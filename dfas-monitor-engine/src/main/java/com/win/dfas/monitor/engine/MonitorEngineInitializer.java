/****************************************************
 * 创建人：  @author wangyaoheng    
 * 创建时间: 2019-7-8/16:51
 * 项目名称: ibt-connector
 * 文件名称: MonitorEngineInitializer.java
 * 文件描述: 负责服务方参数初始化，配置文件装载等工作
 * 公司名称: 深圳市赢时胜信息技术有限公司
 *
 * All rights Reserved, Designed By 深圳市赢时胜信息技术有限公司
 * @Copyright:2016-2019
 *
 ********************************************************/
package com.win.dfas.monitor.engine;


import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.engine.pool.ScheduledThreadPool;
import com.win.dfas.monitor.engine.task.HomeMessagePushTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;

/**
 * 交易类接口应用初始化
 * 包名称：com.win.dfas.monitor.engine
 * 类名称：MonitorEngineInitializer
 * 类描述：线程池启动，服务推送等初始化工作
 * 创建人：@author wangyaoheng
 * 创建时间：2019-08-07/16:19
 */
public class MonitorEngineInitializer implements SmartLifecycle {

    static Logger logger = LoggerFactory.getLogger(MonitorEngineInitializer.class);

    public static boolean isRunning = false;

    public static final String userDir = System.getProperty("user.dir");

    @Override
    public void start() {
        try {

            logger.debug("userDir=" + userDir);

            // 交易类接口应用初始化
            if (!isRunning) {

                isRunning = true;
                logger.debug("ibt初始化");

                //-----------------------------------------
               // ScheduledThreadPool.init();
                //ScheduledThreadPool.scheduleAtFixedRate(new HomeMessagePushTask(), 1000, 5 * DateUtils.MILLIS_PER_SECOND);

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("imix应用初始化异常" + e.getMessage());
            isRunning = false;
        }
    }


    @Override
    public void stop() {
        // 系统清理工作
        if (isRunning) {
            isRunning = false;
        }

    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }


}
