package com.cui.plateweb.comm;

import com.cui.plateweb.comm.spring.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 获取i18n资源文件
 *
 * @author
 */
public class MessageUtils
{
    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String message(String code, Object... args)
    {
        // 因为这是在工具类中，属于不受Spring管理的环境，所以通过SpringUtils.getBean(MessageSource.class)来获取MessageSource这个Bean
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
//        LocaleContextHolder.getLocale();
        System.out.println("0000");
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
