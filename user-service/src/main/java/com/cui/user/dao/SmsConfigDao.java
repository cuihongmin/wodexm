package com.cui.user.dao;


import com.cui.user.entity.SmsConfig;
import org.springframework.stereotype.Component;

@Component
public interface SmsConfigDao {

	SmsConfig selectSmsConfig();

    
}