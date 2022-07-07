package com.cui.user.dao;

import com.cui.user.entity.SmsTemplate;
import org.springframework.stereotype.Component;

@Component
public interface SmsTemplateMapper {
    // 发送短信
    SmsTemplate selectSmsTemplateByKey(String key);

}
