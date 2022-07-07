package com.cui.plateweb.comm.security.handle;


import com.alibaba.fastjson.JSON;
import com.cui.common.constant.Constants;
import com.cui.common.constant.HttpStatus;
import com.cui.plateweb.comm.manage.AsyncManager;
import com.cui.common.utils.BaseResult;
import com.cui.common.utils.StringUtil;
import com.cui.plateweb.comm.ServletUtils;
import com.cui.plateweb.comm.security.service.TokenService;
import com.cui.plateweb.factory.AsyncFactory;
import com.cui.plateweb.factory.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* 自定义退出处理类 返回成功
*
* @author cuihongmin
*/
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;
    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtil.isNotNull(loginUser)){
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            //记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(BaseResult.fail(HttpStatus.SUCCESS, "退出成功")));

    }
}
