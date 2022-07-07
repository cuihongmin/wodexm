package com.cui.plateweb.config;

import com.cui.plateweb.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* @Author cuihongmin
* 配置拦截器和参数解析器
* @Date
* @Param
* @return */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	TokenInterceptor tokenInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
		// 添加不拦截路径
		// registration.excludePathPatterns("/","/login","/error","/static/**","/logout");
	}
}
