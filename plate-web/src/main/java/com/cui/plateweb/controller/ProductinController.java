package com.cui.plateweb.controller;

import com.cui.user.service.ProductService;
import com.cui.common.utils.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/product")
@Api(value="/product",tags="测试学习相关接口")
public class ProductinController {

    @Autowired
    private ProductService productService;

    @ApiOperation("测试连接redis接口")
    @GetMapping("/add")
    public BaseResult testadd() {

        try {
            return productService.addInfoToRedis();
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统内部错误");
        }
    }

    // 发送短信
    @ApiOperation(value = "发送注册验证码", nickname = "验证码", notes = "验证码", produces = "application/json")
    @GetMapping("/get")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "mobile", value = "手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "tmpKey", value = "发送短信模板", required = true, dataType = "String"),
    })
    public BaseResult getSendCode(String mobile, String tmpKey) {
        try {
//            return BaseResult.fail("501", "该功能正在建设中，敬请等待");
            return productService.sendSmsWithCode(mobile, tmpKey);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统内部错误");
        }
    }

}

