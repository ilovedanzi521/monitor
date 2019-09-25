package com.win.dfas.monitor.exporter.microservice.center;

/**
 * 包名称：com.win.dfas.monitor.exporter.microservice
 * 类名称：AbstractMonitorCenter
 * 类描述：负责微服务相关监控数据的执行
 * 创建人：@author wangyaoheng
 * 创建时间：2019年9月25日/下午1:31:49
 */
public abstract class AbstractMonitorCenter {

    /**
     * 获取查询实例
     * @return 实例对象
     */
    public static AbstractMonitorCenter instance() {
        return MonitorCenterImpl.getInstance();
    }

    public abstract void execute();

}
