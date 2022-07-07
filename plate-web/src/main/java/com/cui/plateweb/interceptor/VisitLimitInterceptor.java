package com.cui.plateweb.interceptor;

import com.cui.common.utils.BaseResult;
import com.cui.common.utils.DateUtil;
import com.cui.plateweb.comm.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class VisitLimitInterceptor {

    //接口限制单位时间(单位：秒)
    public static final long LIMIT_TIME = 60;
    //接口限制单位时间内的次数
    public static final long LIMIT_COUNT = 10;
    //接口限制一天内访问的总次数
    public static final long LIMIT_TOTAL_COUNT = 30;
    //访问次数过多，暂缓时间(单位：秒)
    public static final int REST_TIME = 3600;
    //redis缓存失效时间(单位：秒)
    public static final int TIMEOUT = 86400;

    @Autowired
    RedisUtil redisUtil;

    @Pointcut("@annotation(com.cui.plateweb.annotation.VisitLimit)")
    public void visitLimit() {

    }

    @Around("visitLimit()")  // 环绕增强，相当于MethodInterceptor
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String path = request.getServletPath();
        String deviceToken = request.getParameter("deviceToken");
        String type = request.getParameter("type");
        if (deviceToken==null||"".equals(deviceToken)||type==null ||"".equals(type)){
            return BaseResult.fail("502","传入的参数非法！");
        }
        //对同一个接口频繁调用进行限制
        BaseResult limitStatus = interfaceLimit(path,deviceToken,type);
        if (!limitStatus.isSuccess()){
            return limitStatus;
        }
        Object ob = pjp.proceed();// ob 为方法的返回值
        return ob;
    }
    /**
     * 接口频繁访问限制
     * @param path 接口地址
     * @param deviceToken 设备ID
     * @return
     */
    public BaseResult interfaceLimit(String path,String deviceToken,String type){
        String date = DateUtil.getDateToStrTime(new Date(),"yyyy-MM-dd");
        String keyPref = "limit_"+date+"_"+path+"_"+deviceToken+type;
        String limitTotalCount = keyPref+"_LTC";
        String totalCountStr = (String)redisUtil.get(limitTotalCount);
        Long totalCount = Long.parseLong(totalCountStr==null?"0":totalCountStr);
        if (totalCount>=LIMIT_TOTAL_COUNT){
            return BaseResult.fail("502","今日获取次数已达上限，请明日再试！");
        }
        String limitRestTime =  keyPref+"_RT";
        long restTime = (Long)redisUtil.getExpire(limitRestTime);
        if (restTime>0){
            return BaseResult.fail("502","操作过于频繁请稍后再试！");

        }
        String limitTime = keyPref+"_LT";
        String limitCount = keyPref+"_LC";
        String timeStr = (String)redisUtil.get(limitTime);
        Long time = Long.parseLong(timeStr==null?"0":timeStr);
        String countStr = (String)redisUtil.get(limitCount);
        Long count= Long.parseLong(countStr==null?"0":countStr);
        if(time!=0 && count!=0 && totalCount!= 0){
            long timeCha = (System.currentTimeMillis()-(long)time)/1000;
            if(timeCha<= LIMIT_TIME){
                if(count<LIMIT_COUNT){
                    count=count+1;
                    totalCount = totalCount+1;
                    redisUtil.set(limitCount,count+"",TIMEOUT);
                    redisUtil.set(limitTime,time+"",TIMEOUT);
                    redisUtil.set(limitTotalCount,totalCount+"",TIMEOUT);
                }else {
                   redisUtil.set(limitRestTime,"暂缓",REST_TIME);
                   restTime = (Long)redisUtil.getExpire(limitRestTime);
                   return BaseResult.fail("502","操作过于频繁请稍后再试！");

                }
            }else {
                totalCount = totalCount+1;
                redisUtil.set(limitTime,System.currentTimeMillis()+"",TIMEOUT);
                redisUtil.set(limitCount,"1",TIMEOUT);
                redisUtil.set(limitTotalCount,totalCount+"",TIMEOUT);
            }
        }else {
            redisUtil.set(limitTime,System.currentTimeMillis()+"",TIMEOUT);
            redisUtil.set(limitCount,"1",TIMEOUT);
            redisUtil.set(limitTotalCount,"1",TIMEOUT);
        }
        return BaseResult.success();
    }
}
