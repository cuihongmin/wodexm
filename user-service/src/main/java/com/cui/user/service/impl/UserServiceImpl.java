package com.cui.user.service.impl;

import com.cui.common.constant.Constants;
import com.cui.common.utils.BaseResult;
import com.cui.common.utils.CommonUtils;
import com.cui.common.utils.MD5Util;
import com.cui.common.utils.StringUtil;
import com.cui.user.comm.RedisUtil;
import com.cui.user.dao.WdUserDao;

// 待会看
//import com.cui.user.entity.LoginUser;
import com.cui.user.entity.UserLoginLogs;

import com.cui.user.entity.WdUser;
import com.cui.user.service.ProductService;
import com.cui.user.service.WdUserService;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cui.user.entity.UserOperationLogs;

//import org.springframework.security.authentication.AuthenticationManager;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;


import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements WdUserService {

    @Autowired
    private WdUserDao wdUserDao;
    @Autowired
    RedisUtil redisUtil;
//    @Autowired
//    private RedisCache redisCache;
    @Autowired
    private ProductService productService;
//    @Autowired
//    private AuthenticationManager authenticationManager;

    private static final long time = -1;


    // 登录
    @Override
    public BaseResult login(String mobile, String password, String ip, String sessionId) {
        // 验证
        if (!CommonUtils.isMobile(mobile)) {
            return BaseResult.fail("501", "电话号码格式不正确");
        }
        // user是否存在
        WdUser user = wdUserDao.selectUserByMobile(mobile);
        if (user == null) {
            return BaseResult.fail("502", "用户不存在");
        }
        if (!user.getPassword().equals(MD5Util.MD5(password))) {
            return BaseResult.fail("502", "密码不正确");
        }
        user.setMobile(mobile);
        //验证通过 保存token 返回 登录成功
        String key = "user_"+user.getUid();
        String token = (String)redisUtil.get(key);
        if (token==null || "".equals(token)){
            token = StringUtil.get32UUID();
            boolean is = redisUtil.set(key, token,time);
            if(!is) {
                return BaseResult.fail("502", "登录失败");
            }
        }
        // 记录UserLoginlogs
        UserLoginLogs userLoginlogs = new UserLoginLogs();
        userLoginlogs.setUid(user.getUid());
        userLoginlogs.setLoginIP(ip);
//        userLoginlogs.setChannel(channel);
        userLoginlogs.setSessionId(sessionId);
        int loginCount = wdUserDao.insertUserLoginLogs(userLoginlogs);
        if (loginCount <= 0) {
            return BaseResult.fail("502", "更新登录日志失败");
        }
        // 清空密码(密码不需要返回给前端)
        user.setPassword(null);
        user.setToken(token);


        return BaseResult.success(200,user);


    }

    // 发送注册验证码
    @Override
    public WdUser getUserByMobile(String mobile) {
//        WdUser wdUser = new WdUser();
//
//        String token = StringUtil.get32UUID();
//        String userKey = Constants.LOGIN_TOKEN_KEY + token;
//        wdUser.setMobile(mobile);
//
//        redisUtil.set(userKey,wdUser,time);
//        System.out.println("redis缓存对象成功");
        // 根据电话号码 查用户
        return wdUserDao.selectUserByMobile(mobile);
    }

    // 注册
    @Transactional
    @Override
    public BaseResult registerWD(String mobile, String password, String code) throws Exception {

        WdUser wdUser = new WdUser();
        if (!CommonUtils.isMobile(mobile)) {
            return BaseResult.fail("501", "电话号码格式不正确");
        }
        // 验证码校验

        if(null != code && !"".equals(code)){
            BaseResult baseResult = productService.checkCode(mobile, "register", code);
            if (!baseResult.isSuccess()) {
                return baseResult;
            }
        }
        // 手机号码是否存在
        WdUser user= wdUserDao.selectUserByMobile(mobile);
        if(user != null){
            return BaseResult.fail("502", "电话号码已注册");
        }
        int count = 0;
        // 注册入库
        wdUser.setMobile(mobile);
        wdUser.setPassword(MD5Util.MD5(password));
        count = wdUserDao.insertUser(wdUser);
        if(count==0){
            return BaseResult.fail("502", "注册失败");
        }
//        // 新用户添加默认角色
//        List<UserRole> list = new ArrayList<>();
//        TypeEnum []  types = TypeEnum.values();
//        for (int i = 0; i < types.length; i++) {
//            UserRole userRole = new UserRole();
//            userRole.setUid(newUser.getUid());
//            userRole.setRole(types[i].getValue());
//            list.add(userRole);
//        }
//        int insertCount = userRoleDao.insertByBatch(list);
//        if (insertCount != list.size()) {
//            throw new Exception("注册系统角色失败");
//        }
//        // 用户验证
//        Authentication authentication = null;
//        try {
//            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
//            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobile, password));
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//            return BaseResult.fail("500", "用户不存在/密码错误!");
//        }
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        // 注册成功 保存token
        String key = "user_"+wdUser.getUid();
        String token = StringUtil.get32UUID();


        redisUtil.set(key, token, time);
        wdUser.setToken(token);
        wdUser.setPassword(null);
        redisUtil.set(key,wdUser,time);
        System.out.println("0000");

        return BaseResult.success(wdUser);
    }

    //校验和token
    @Override
    public BaseResult checkUserAuth(String token) {
//        String key = Constants.LOGIN_TOKEN_KEY + token;
        System.out.println("aaa" + token);
        String key = Constants.LOGIN_TOKEN_KEY + token;
        if (!StringUtil.checkNotNull(token)) {
            return BaseResult.fail("600", "token can not be empty");
        }
//        if (!StringUtil.checkNotNull(uid)) {
//            return BaseResult.fail("600", "uid can not be empty");
//        }
        String redisToken = (String) redisUtil.get(key);
        if (!StringUtil.checkNotNull(redisToken)) {
            return BaseResult.fail("600", "login timeout");
        }
        if (!token.equals(redisToken)) {
            return BaseResult.fail("600", "login timeout");
        }
        //TODO 更新token数据
        if(time<=0){
            boolean is = redisUtil.set(key, token,time);
            if(is){
                return BaseResult.success();
            }
        }
        return BaseResult.success();
    }

    // 获取用户信息
    @Override
    public BaseResult getUserDealList() {
            BaseResult base = new BaseResult();
            List<WdUser> list = wdUserDao.getUserDealList();
            base.setData(list);
            base.setSuccess(true);
            base.setCode(200);
            return base;
    }

    // 修改密码
    @Override
    public BaseResult updatePasswordAtPC(String mobile, String code, String newPassword, Integer uid) {
        // 根据uid查询客户
        WdUser user = wdUserDao.selectUserByMobile(mobile);
        if (user == null) {
            return BaseResult.fail("502", "非法用户，请先注册");
        }
        //短信验证
        BaseResult baseResult = productService.checkCode(mobile,"change_mobile",code);
        if (!baseResult.isSuccess()) {
            return baseResult;
        }
        int count = wdUserDao.updatePasswordById(user.getUid(), MD5Util.MD5(newPassword));
        if (count <= 0) {
            return BaseResult.fail("502", "修改密码失败");
        }
        UserOperationLogs userOperationLogs = new UserOperationLogs();
        userOperationLogs.setUid(uid);
        userOperationLogs.setOperation("updatePassword");
        userOperationLogs.setUpdateData(MD5Util.MD5(newPassword));
        userOperationLogs.setCreateTime(new Date());
        int operationlogsCount = wdUserDao.insertUserOperationLogs(userOperationLogs);
        if (operationlogsCount <= 0) {
            return BaseResult.fail("502", "更新修改密码日志失败");
        }
        return BaseResult.success(user);
    }

   

    // 修改手机号
    @Override
    public BaseResult updateMobileById(String oldMobile, String newMobile, Integer uid, String oldMobileCode, String newMobileCode) {
        return null;
    }

    /**
     * 验证码登录4.0
     * @param mobile
     * @param code
//     * @param channel
     * @param ip
     * @param sessionId
     * @return
     */
    @Transactional
    @Override
    public BaseResult loginByCode(String mobile, String code, String ip, String sessionId) {
        // 验证
        if (!CommonUtils.isMobile(mobile)) {
            return BaseResult.fail("501", "电话号码格式不正确");
        }
        // user是否存在
        WdUser user = wdUserDao.selectUserByMobile(mobile);
        if (user == null) {
            return BaseResult.fail("502", "用户不存在");
        }
        //短信验证
        BaseResult baseResult = productService.checkCode(mobile,"login_mobile",code);
        if (!baseResult.isSuccess()) {
            return baseResult;
        }
        user.setMobile(mobile);
        //验证通过 保存token 返回 登录成功
        String key = "user_"+user.getUid();
        String token = (String)redisUtil.get(key);
        if( token==null || "".equals(token)){
            token = StringUtil.get32UUID();
            boolean is = redisUtil.set(key, token,time);
            if(!is) {
                return BaseResult.fail("502", "登录失败");
            }
        }
        user.setToken(token);
        // 记录UserLoginlogs
        UserLoginLogs userLoginlogs = new UserLoginLogs();
        userLoginlogs.setUid(user.getUid());
        userLoginlogs.setLoginIP(ip);
//        userLoginlogs.setChannel(channel);
        userLoginlogs.setSessionId(sessionId);
        int loginCount = wdUserDao.insertUserLoginLogs(userLoginlogs);
        if (loginCount <= 0) {
            return BaseResult.fail("502", "更新登录日志失败");
        }
        user.setPassword(null);
        return BaseResult.success(user);
    }

}
