package com.cui.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.cui.common.utils.*;
import com.cui.user.comm.EnsmsSend;
import com.cui.user.dao.SmsConfigDao;
import com.cui.user.dao.SmsSendLogsDao;
import com.cui.user.dao.SmsTemplateMapper;
import com.cui.user.entity.*;
import com.cui.user.service.ProductService;
import com.cui.common.utils.BaseResult;
import com.cui.user.comm.RedisUtil;
import com.cui.common.utils.VerifyCodeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;

//import org.springframework.data.redis.core.RedisTemplate;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SmsTemplateMapper smsTemplateMapper;
    @Autowired
    private SmsConfigDao smsConfigDao;
    @Autowired
    SmsSendLogsDao smsSendLogsDao;

    // 消息队列目的地名称
    private static final String DESTINATIONNAME = "SMSMESSAGE";


   @Override
   public BaseResult addInfoToRedis() {
       ProductXin xin = new ProductXin("103", "王老吉308", 2.0);
       BaseResult base = new BaseResult();
       try {
          String jsonstr = new ObjectMapper().writeValueAsString(xin);
            boolean b = redisUtil.set(xin.getProductId(), jsonstr);
            base.setSuccess(b);
            base.setCode(200);
            base.setData(xin);
            return base;
//           stringRedisTemplate.boundValueOps(xin.getProductId()).set(jsonstr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return BaseResult.fail("500", "系统内部错误");
        }

    }

    @Override
    public BaseResult sendSmsWithCode(String mobile, String tmpKey) {
        SmsTemplate smsTemplate = smsTemplateMapper.selectSmsTemplateByKey(tmpKey);
        String content = smsTemplate.getContent();
        String code = VerifyCodeUtil.generateTextCode(0, 6); // 生成验证码
        content = content.replaceFirst("@", code);
                System.out.println(content);
        //boolean sucess = redisUtil.set("code_" + tmpKey + "_" + mobile, code, 15 * 60);
        boolean sucess = redisUtil.set("code_" + tmpKey + "_" + mobile, code, 24*60*60);
        if (!sucess) {
            return BaseResult.fail("001", "短信发送失败");
        }
        MqMsg MqMsg = new MqMsg(mobile, content, "sendSms");
        // 向消息队列发送消息(Queue)
        // jmsTemplate向ActiveMQ发送Queue类型消息  有两种方式
        // 方法一: send()  send 方法需要我们重写 send 方法，好处是可以在内部多一些自定义的逻辑内容，坏处就是代码量有点多。
        //代码里输出信息有助于我们了解到程序运行的时间节点。
        // 方法二: convertAndSend() 比较简单。
        jmsTemplate.convertAndSend(DESTINATIONNAME, JSON.toJSONString(MqMsg));
        return BaseResult.success();

    }
    @Override
    public BaseResult checkCode(String mobile, String tmpKey, String code) {
        String redisCode = (String) redisUtil.get("code_" + tmpKey + "_"
                + mobile);
        if (redisCode == null) {
            return BaseResult.fail("001", "验证码验证失败");
        }
        if (!code.equals(redisCode)) {
            return BaseResult.fail("001", "验证码验证失败");
        }
        return BaseResult.success(null);
    }
    // 消息队列activeMQ消费者监听队列接收消息
    @JmsListener(destination = DESTINATIONNAME, containerFactory = "jmsQueueListener")
    public void receiveQueue(final TextMessage text, Session session) throws JMSException {
        System.out.println("消息消費者收到短信报文为:"+text.getText());
        LOGGER.debug("开始发送短信");
        SmsSendLogs smsSendLogs = new SmsSendLogs();
        try {
            SmsConfig smsConfig = smsConfigDao.selectSmsConfig();
            String header = smsConfig.getHeader();
            MqMsg mqMsg = JSON.parseObject(text.getText(), MqMsg.class);
            System.out.println(mqMsg);
            String mobile = mqMsg.getMobile();
            String content = mqMsg.getContent();
            content = header + content;
            BaseResult base = EnsmsSend.send(smsConfig.getRequestUrl(),smsConfig.getUserid(), smsConfig.getAccount(),smsConfig.getPassword(), mobile, content);
            smsSendLogs.setMobile(mobile);
            smsSendLogs.setContent(content);
            smsSendLogs.setCreateTime(new Date());
            if (base.isSuccess()) {
                text.acknowledge();
                smsSendLogs.setStatus(1);
            }else{
                smsSendLogs.setContent(base.getData()+"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.recover();
        }finally{
            smsSendLogsDao.insertSmsSendLogs(smsSendLogs);
        }
    }
}
