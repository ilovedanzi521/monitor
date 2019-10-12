
package com.win.dfas.monitor.config.datasource.aspectj.annotation;


import com.win.dfas.monitor.config.datasource.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义多数据源切换注解
 * 用法见类com.yss.bi.iae.report.service.report.GraphReportServiceImpl的selectReportByIdFromSlave方法
 *
 * @author wangyh
 * @version 1.0.0 2018-11-13
 * @since 1.0.0 2018-11-13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {

    /**
     * 切换数据源名称
     */
    DataSourceType value() default DataSourceType.MASTER;
}
