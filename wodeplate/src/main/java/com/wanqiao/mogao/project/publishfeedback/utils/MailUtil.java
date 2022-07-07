package com.wanqiao.mogao.project.publishfeedback.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

/**
 * 发送邮件
 */
public class MailUtil {


    /**
     *
     * @param sender
     * @param subject  主题
     * @param from  邮件发送着
     * @param to  邮件接受着
     * @param cc  邮件抄送给谁
     * @param content  邮件内容
     */
    public static void sendMail(JavaMailSender sender, String subject,
                                String from, String to, String cc, String content) throws  Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
//        message.ject(subject);
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setText(content);
        message.setSentDate(new Date());
        sender.send(message);
    }
}
