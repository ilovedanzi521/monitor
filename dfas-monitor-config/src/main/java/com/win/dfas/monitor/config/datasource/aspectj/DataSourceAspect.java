
package com.win.dfas.monitor.config.datasource.aspectj;

import com.win.dfas.monitor.config.datasource.DynamicDataSourceContextHolder;
import com.win.dfas.monitor.config.datasource.aspectj.annotation.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 主从数据源切换
 * 用法见类com.yss.bi.iae.report.service.report.GraphReportServiceImpl的selectReportByIdFromSlave方法
 * @author wangyh
 * @version 1.0.0 2018-11-13
 * @since 1.0.0 2018-11-13
 */
@Aspect
@Order(-1)
@Component
public class DataSourceAspect {

    static Logger log= LoggerFactory.getLogger(DataSourceAspect.class);


    /**
     * 改变数据源
     * @param joinPoint
     * @param targetDataSource
     */
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        String dsType = targetDataSource.value().name();

        if (!DynamicDataSourceContextHolder.isContainsDataSource(dsType)) {
            log.error("数据源 " + dsType + " 不存在使用默认的数据源 -> " + joinPoint.getSignature());
        } else {
            log.info("使用数据源：" + dsType);
            DynamicDataSourceContextHolder.setDateSoureType(dsType);
        }
    }

    @After("@annotation(targetDataSource)")
    public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        log.info("清除数据源 " + targetDataSource.value().name() + " !");
        DynamicDataSourceContextHolder.clearDateSoureType();
    }
}
