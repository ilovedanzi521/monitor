package com.win.dfas.monitor.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 配置启用注解
 *
 * @author wangyh
 * @version 1.0.0 2018-12-05
 * @since 1.0.0 2018-12-05
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ WebSocketConfig.class })
public @interface EnableMonitorWebSocket {

}
