package com.win.dfas.monitor.web.enable;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.win.dfas.monitor.exporter.microservice.metrics.MonitorMetricsAspect;
import org.springframework.context.annotation.Import;

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
@Import({ MonitorMetricsAspect.class })
public @interface EnableMonitorConfig {

}
