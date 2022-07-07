package com.cui.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;




@SpringBootApplication
@EnableScheduling
@EnableCaching
@MapperScan(basePackages = {"com.cui.user.dao"})
@ImportResource(locations = {"classpath:dubbo-provider.xml","classpath:dubbo-consumer.xml"})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)

public class SpringbootApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringbootApplication.class, args);
    	new SpringApplicationBuilder(com.cui.user.SpringbootApplication.class).web(WebApplicationType.NONE).run(args);
    }
    
    
}