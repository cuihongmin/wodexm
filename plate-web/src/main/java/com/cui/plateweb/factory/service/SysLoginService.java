package com.cui.plateweb.factory.service;
import com.cui.common.constant.Constants;
import com.cui.common.utils.BaseResult;
import com.cui.plateweb.comm.IdUtils;
import com.cui.plateweb.comm.manage.AsyncManager;
import com.cui.plateweb.comm.MessageUtils;
import com.cui.plateweb.comm.RedisCache;
import com.cui.plateweb.comm.security.service.TokenService;
import com.cui.plateweb.exception.CustomException;
import com.cui.plateweb.exception.user.CaptchaException;
import com.cui.plateweb.exception.user.UserPasswordNotMatchException;
import com.cui.plateweb.factory.AsyncFactory;

import com.cui.plateweb.factory.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Component
public class SysLoginService  {
    @Autowired
    private RedisCache redisCache;
    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
//
    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */


    public String login(String username, String password, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        // 从redis获取验证码
        String captcha = redisCache.getCacheObject(verifyKey);
        // 从redis中删除验证码
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaException();
        }
        // equalsIgnoreCase()⽅法⽤于将字符串与指定的对象⽐较，不考虑⼤⼩写.
        if (!code.equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        System.out.println("开始用户验证...");
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        LoginUser loginUser = new LoginUser();
        String message = "登录成功";
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, message));
        System.out.println("99999" + authentication.getPrincipal());

//        LoginUser loginUser1 = (LoginUser) authentication.getPrincipal();
//        System.out.println("88888" + loginUser1);
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        String token = IdUtils.fastUUID();
        System.out.println("我的token" + token);
        String userKey = Constants.LOGIN_TOKEN_KEY + token;
        redisCache.setCacheObject(userKey, token, 9320, TimeUnit.MINUTES);
        return token;

    }
}
