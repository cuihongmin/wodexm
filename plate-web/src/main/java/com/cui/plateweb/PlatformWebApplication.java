package com.cui.plateweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ImportResource(locations = {"classpath:dubbo-provider.xml","classpath:dubbo-consumer.xml"})
public class PlatformWebApplication {

	public static ApplicationContext application;

	public static void main(String[] args) {
		application = SpringApplication.run(com.cui.plateweb.PlatformWebApplication.class, args);
	}

	
}