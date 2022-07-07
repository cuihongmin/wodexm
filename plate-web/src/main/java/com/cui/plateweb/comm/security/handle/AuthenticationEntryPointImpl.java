package com.cui.plateweb.comm.security.handle;

import com.alibaba.fastjson.JSON;
import com.cui.common.constant.HttpStatus;
import com.cui.common.utils.BaseResult;
import com.cui.common.utils.StringUtil;
import com.cui.plateweb.comm.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
/**
* @Author cuihongmin
* @Descripyion 自定义认证失败处理类,
* @Date
* @Param
* @return  请求访问：{}，认证失败，无法访问系统资源
 * */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException{
        System.out.println("执行了自定义认证失败处理");
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(BaseResult.fail(code, msg)));


    }
}
