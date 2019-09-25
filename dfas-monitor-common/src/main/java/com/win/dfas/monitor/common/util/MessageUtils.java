package com.win.dfas.monitor.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * 获取i18n资源文件
 *
 * @author ruoyi
 */
public class MessageUtils {

    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return
     */
    public static String message(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        MessageSource messageSource = SpringContextUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, locale);
    }

}
