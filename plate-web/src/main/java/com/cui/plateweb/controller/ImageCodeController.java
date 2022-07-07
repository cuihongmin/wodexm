package com.cui.plateweb.controller;

import com.cui.common.constant.Constants;
import com.cui.common.utils.BaseResult;
import com.cui.common.utils.VerifyCodeUtils;
import com.cui.common.utils.sign.Base64;
import com.cui.plateweb.comm.IdUtils;
import com.cui.plateweb.comm.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/imageCode")
@Api(value = "imageCode", tags = "图形验证码")
public class ImageCodeController {
    @Autowired
    RedisCache redisCache;
    /**
     * 图像验证码
    * */
    @ApiOperation(value = "图像验证码")
    @GetMapping("/get")
    @ApiImplicitParams({})
    public BaseResult getImageCode(HttpServletResponse response) throws IOException {
        // 生成随机字符串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        // 将生成的uuid和验证码 redis key放入redis缓存
        redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w,h,stream,verifyCode);
        try {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("uuid",uuid);
            map.put("img", Base64.encode(stream.toByteArray()));
            return BaseResult.success(200,map);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统异常");
        }
        finally {
            // 关闭流
            stream.close();
        }

    }
}
