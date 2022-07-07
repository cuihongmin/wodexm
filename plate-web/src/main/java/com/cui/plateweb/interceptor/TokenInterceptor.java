package com.cui.plateweb.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cui.common.utils.BaseResult;
import com.cui.plateweb.annotation.Token;
import com.cui.user.service.WdUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;


@Component
public class TokenInterceptor implements HandlerInterceptor {


	private static Logger log = LoggerFactory.getLogger(com.cui.plateweb.interceptor.TokenInterceptor.class);

	@Value("${token.header}")
	private String header;
	
	@Autowired
	private WdUserService userService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("验证token");
		try {
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				Method method = handlerMethod.getMethod();
				Token annotation = method.getAnnotation(Token.class);
				if (null != annotation) {
					if (annotation.isToken()) {
//						String uid = httpServletRequest.getParameter("uid");
						String token = httpServletRequest.getHeader(header);
//						if(uid==null || "".equals(uid)){
//							return returnFalse(response,null, "uid Can not be empty");
//						}
//						if(token==null || "".equals(token)){
//							return returnFalse(response, null,"token Can not be empty");
//						}
						// 校验token是否过期
						BaseResult result = userService.checkUserAuth(token);
						if(result.isSuccess()) {
							return true;
						}else{
							return returnFalse(response, result,result.getErrorMsg());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private boolean returnFalse(HttpServletResponse response,BaseResult base,String message){
		try {
			if(base==null){
				base = new BaseResult();
			}
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-type", "text/html;charset=UTF-8");  
			response.setContentType("application/json; charset=utf-8");
			base.setErrorMsg(message);
			out.print(new ObjectMapper().writeValueAsString(base));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
