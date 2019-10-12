package com.win.dfas.monitor.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @动态数据源
 * @author wangyh
 * @version 1.0.0 2018-11-26
 * @since 1.0.0 2018-11-26
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * DynamicDataSourceContextHolder获取当前线程的DatabaseType
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDateSoureType();
    }

}