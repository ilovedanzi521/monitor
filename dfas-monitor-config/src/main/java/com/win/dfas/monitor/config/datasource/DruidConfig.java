
package com.win.dfas.monitor.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * druid 配置多数据源
 *
 * @author wangyh
 * @version 1.0.0 2018-11-26
 * @since 1.0.0 2018-11-26
 */
@Configuration
public class DruidConfig {

    /**
     * 存放主从数据源，用法见类com.yss.bi.iae.framework.MultiplyDataSourceServiceImpl的selectGraphReportFromSlave2方法
     */
    public static Map<String, DataSource> dsMap = new ConcurrentHashMap<String, DataSource>();

    /**
     * 首选数据源
     * @return
     */
    @Bean(name = "masterDataSource")
    @Qualifier("masterDataSource")
    public DataSource masterDataSource() {
        return buildMasterDataSource(DataSourceType.MASTER);
    }

    /**
     * 次选数据源
     * @return
     */
    @Bean(name = "slaveDataSource")
    @Qualifier("slaveDataSource")
    public DataSource newSlaveDataSource() {
        return buildSlaveDataSource(DataSourceType.SLAVE);
    }

    /**
     * 实例化首选数据源
     * @param dataSourceType
     * @return
     */
    private DataSource buildMasterDataSource(DataSourceType dataSourceType) {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 实例化次选数据源
     * @param dataSourceType
     * @return
     */
    private DataSource buildSlaveDataSource(DataSourceType dataSourceType) {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 首选数据源Jdbc模板
     * @param dataSource
     * @return
     */
    @Bean(name = "masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 次选数据源Jdbc模板
     * @param dataSource
     * @return
     */
    @Bean(name = "slaveJdbcTemplate")
    public JdbcTemplate slaveJdbcTemplate(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * dynamicDataSource作为primary数据源注入到SqlSessionFactory的dataSource属性中去，并且该dataSource作为transactionManager的入参来构造DataSourceTransactionManager
     *
     * @return
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
        targetDataSources.put(DataSourceType.SLAVE.name(), slaveDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add(DataSourceType.MASTER.name());
        DynamicDataSourceContextHolder.dataSourceIds.add(DataSourceType.SLAVE.name());
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    /**
     * 该数据源用于获取数据仓库表结构和表字段，这种方式获取连接后务必手动关闭数据源和连接
     * 用法见测试类com.yss.bi.iae.framework.MultiplyDataSourceTestCase的selectGraphReport方法
     *
     * @param dsMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public static DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName("com.alibaba.druid.pool.DruidDataSource");
            String driverClassName = dsMap.get("driverClassName").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();

            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
