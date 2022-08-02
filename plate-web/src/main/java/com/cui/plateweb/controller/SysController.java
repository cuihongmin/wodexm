package com.cui.plateweb.controller;

import com.alibaba.fastjson.JSON;
import com.cui.common.utils.BaseResult;
import com.cui.common.utils.StringUtil;
import com.cui.common.utils.sign.MoGaoAES;
import com.cui.plateweb.annotation.Token;
import com.cui.plateweb.comm.RedisCache;
import com.cui.plateweb.comm.RedisUtil;
import com.cui.plateweb.comm.ServletUtils;
import com.cui.plateweb.comm.security.service.TokenService;
import com.cui.plateweb.factory.LoginUser;
import com.cui.plateweb.factory.service.SysPermissionService;
import com.cui.user.entity.LoginBody;
//import com.cui.plateweb.factory.LoginUser;
import com.cui.plateweb.factory.service.SysLoginService;
import com.cui.user.entity.PostMan;
import com.cui.user.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/sysUser")
@Api(value="/order",tags="用户接口2")
public class SysController {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysLoginService sysLoginService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysPermissionService permissionService;

    private static final int expireTime = 4320;
    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    @ApiOperation(value = "登录接口2(在登陆的同时生成token)")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "code", value = "图片验证码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "uuid", value = "uuid", required = true, dataType = "String"),
            /*@ApiImplicitParam(paramType = "query",name = "imageCode", value = "图形验证码", required = true, dataType = "String"),*/
            /*@ApiImplicitParam(paramType = "query",name = "key", value = "验证码key", required = true, dataType = "String")*/
    })
    public BaseResult login(@RequestBody LoginBody loginBody) {
        BaseResult base = new BaseResult();
        System.out.println("第一次：" + loginBody.getPassword());
        try {
            loginBody.setPassword(MoGaoAES.getInstance().Decrypt(loginBody.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统异常");
        }

        System.out.println("第二次：" + loginBody.getPassword());

        // 生成令牌
        String token = sysLoginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token",token);
        base.setData(params);
        base.setCode(200);
        base.setSuccess(true);
        return base;
    }

    @Token
    @ApiOperation(value = "获取用户相关信息")
    @GetMapping("/getInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "token", value = "token", required = true, dataType = "String"),
    })
    public BaseResult getInfo() {


        // 这里有问题影响登录后信息的回显
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();

        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        BaseResult base = new BaseResult();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        params.put("roles", roles);
        params.put("permissions", permissions);
        base.setData(params);
        base.setCode(200);
        base.setSuccess(true);
        return base;
    }

    @ApiOperation(value = "自写接口用于postman的测试")
    @PostMapping("/postman")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType = "query", name = "code", value = "图片验证码", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType = "query", name = "uuid", value = "uuid", required = true, dataType = "String"),
            /*@ApiImplicitParam(paramType = "query",name = "imageCode", value = "图形验证码", required = true, dataType = "String"),*/
            /*@ApiImplicitParam(paramType = "query",name = "key", value = "验证码key", required = true, dataType = "String")*/
    })
    public BaseResult getPostman(PostMan postMan) {
        BaseResult base = new BaseResult();
        System.out.println(postMan);
        base.setData(postMan);
        base.setCode(200);
        base.setSuccess(true);
        return base;


    }

}


//        String key = "user6_";
//        String token = StringUtil.get32UUID();
//        LoginUser loginUser = new LoginUser();
//        loginUser.setToken(token);
//        System.out.println("====" + System.currentTimeMillis());
//
//        loginUser.setLoginTime(System.currentTimeMillis());
//        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
////        redisCache.setCacheObject(key,loginUser);
//
//        String s = JSON.toJSONString(loginUser);
//        try {
////            redisCache.setCacheObject(key, s);
//            redisUtil.set(key, s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("000");
//        Object object = redisCache.getCacheObject(key);
//
////        Object object = redisCache.getCacheObject(key);
//
////        HttpServletRequest request = ServletUtils.getRequest();
////        String sessionId = request.getSession().getId();
//        System.out.println("从redis取数据成功" + object);
//
//        return BaseResult.success(200,loginUser);
////    }

