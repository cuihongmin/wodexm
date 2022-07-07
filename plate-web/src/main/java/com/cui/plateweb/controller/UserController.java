package com.cui.plateweb.controller;

import com.cui.common.utils.BaseResult;
import com.cui.plateweb.annotation.Token;
import com.cui.user.entity.WdUser;
import com.cui.user.service.ProductService;
import com.cui.user.service.WdUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@Api(value="/product",tags="用户相关接口")
public class UserController {
    @Autowired
    private WdUserService userService;
    @Autowired
    private ProductService productService;


    // 登录接口
    @ApiOperation(value = "登录", nickname = "登录", notes = "登录", produces = "application/json")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "mobile", value = "手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "password", value = "密码", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType = "query",name = "channel", value = "渠道(pc,wap)", required = true, dataType = "String"),
            /*@ApiImplicitParam(paramType = "query",name = "imageCode", value = "图形验证码", required = true, dataType = "String"),*/
            /*@ApiImplicitParam(paramType = "query",name = "key", value = "验证码key", required = true, dataType = "String")*/
    })
//    @ResponseBody
    public BaseResult login(@RequestBody WdUser wdUser, HttpServletRequest request, HttpServletResponse resp){
        try {
            String sessionId = request.getSession().getId();
            System.out.println("sessionId==================="+sessionId);
            // 获取登录IP地址
            String ip = request.getRemoteHost();
            System.out.println(wdUser.getMobile());
            BaseResult base = userService.login(wdUser.getMobile(), wdUser.getPassword(),ip,sessionId);
            return base;
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统异常");
        }

    }
    /**
     * 发送注册验证码
     */
    @ApiOperation(value = "发送注册验证码(pc专用)", nickname = "验证码", notes = "验证码", produces = "application/json")
    @PostMapping("/sendRegisterCodeWithImageCode")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "mobile", value = "手机号码", required = true, dataType = "String"),
		/*@ApiImplicitParam(paramType = "query",name = "imageCode", value = "图形验证码", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query",name = "key", value = "验证码key", required = true, dataType = "String")*/
    })
    public BaseResult sendRegisterCodeWithImageCode(String mobile,HttpServletRequest request){
        try {
            WdUser user = userService.getUserByMobile(mobile);
            if (user != null) {
                return BaseResult.fail("502", "此用户已注册");
            }
            /*redisUtil.del(key);*/
            return productService.sendSmsWithCode(mobile, "register");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统异常");
        }
    }

    /**
     * 注册
     */
    @ApiOperation(value = "注册", nickname = "注册", notes = "注册", produces = "application/json")
    @PostMapping("/register")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "mobile", value = "手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "code", value = "验证码", required = false, dataType = "String"),
//            @ApiImplicitParam(paramType = "query",name = "channel", value = "渠道(pc/wap/android/ios)", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType = "query",name = "userName", value = "姓名", required = false, dataType = "String"),
//            @ApiImplicitParam(paramType = "query",name = "source", value = "来源(商城/app/小萌采销/危好运/SCM/CRM)", required = false, dataType = "String"),
//            @ApiImplicitParam(paramType = "query",name = "tradeId", value = "交易员", required = false, dataType = "Integer"),
//            @ApiImplicitParam(paramType = "query",name = "inviteCode", value = "用户邀请码", required = false, dataType = "String"),
//            @ApiImplicitParam(paramType = "query",name = "company", value = "公司名称", required = false, dataType = "String"),
            /*@ApiImplicitParam(paramType = "query",name = "imageCode", value = "图形验证码", required = true, dataType = "String"),*/
            /*@ApiImplicitParam(paramType = "query",name = "key", value = "验证码key", required = true, dataType = "String")*/
    })
    @ResponseBody
    public BaseResult register( String mobile, String password, String code, HttpServletRequest request){
        try {
            return userService.registerWD(mobile, password, code);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统异常");
        }

    }

    @Token
    @ApiOperation(value = "获取用户信息" )
    @GetMapping("/getUserDealList")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "token", value = "token", required = true, dataType = "String"),
    })
    @ResponseBody
    public BaseResult getUserDealList(){
        try {
            return userService.getUserDealList();
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统异常");
        }
    }

    /**
     * 安全退出
     */
    @ApiOperation(value = "安全退出")
    @GetMapping("/exitLogin")
    @ResponseBody
    public BaseResult exitLogin(HttpServletRequest request){
        try {
            request.getSession().removeAttribute("user");
            return BaseResult.success(200,null);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统异常");
        }
    }

    /**
     * 修改密码(pc)4.0
     */
    @Token
    @ApiOperation(value = "修改密码(pc)4.0", nickname = "修改密码(pc)4.0", notes = "修改密码(pc)4.0", produces = "application/json")
    @PostMapping("/updatePwdAtPC")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "mobile", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "code", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "newPassword", value = "新密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "uid", value = "用户ID", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query",name = "token", value = "token", required = true, dataType = "String"),
    })
    @ResponseBody
    public BaseResult updatePwdAtPC(String mobile,String code, String newPassword, Integer uid, String token){
        try {
            return userService.updatePasswordAtPC(mobile,code, newPassword, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统异常");
        }
    }
    /**
     * 验证码登录4.0
     */
    @ApiOperation(value = "验证码登录4.0", nickname = "验证码登录4.0", notes = "验证码登录4.0", produces = "application/json")
    @PostMapping("/registerByCode")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "mobile", value = "手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "code", value = "验证码", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType = "query",name = "channel", value = "渠道(pc,wap)", required = true, dataType = "String")
    })
    public BaseResult registerByCode(String mobile,String code,HttpServletRequest request,HttpServletResponse resp){
        try {
            // 获取登录IP地址
            String ip = request.getRemoteHost();
            String sessionId = request.getSession().getId();
            BaseResult base = userService.loginByCode(mobile,code,ip,sessionId);
//            if("pc".equals(channel)){
//                request.getSession().setAttribute("user", base.getData());
//            }
            resp.setHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\"");
            return base;
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统异常");
        }
    }




}
