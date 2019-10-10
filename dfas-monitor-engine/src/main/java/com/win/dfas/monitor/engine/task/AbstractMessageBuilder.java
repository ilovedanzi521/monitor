package com.win.dfas.monitor.engine.task;

import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.vo.PlatformOverviewVO;

import java.text.NumberFormat;
import java.util.Random;

public abstract class AbstractMessageBuilder {

    /** 千分位格式化 */
    protected NumberFormat thousandBitNumberFormat = NumberFormat.getNumberInstance();

    protected void init() {
        thousandBitNumberFormat.setGroupingUsed(true);
        thousandBitNumberFormat.setMaximumFractionDigits(100);
    }

    protected String getPlatformOverviewData() {
        Random random = new Random(System.currentTimeMillis());
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(thousandBitNumberFormat.format(random.nextInt(100)+1)));;
        platformOverview.setTotalHttpRequest(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10000)+1)));
        platformOverview.setTotalMicroService(String.valueOf(thousandBitNumberFormat.format(random.nextInt(1000)+1)));
        platformOverview.setTotalNode(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10)+1)));
        return JsonUtil.toJson(platformOverview);
    }

    protected String getQpsData() {
        Random random = new Random(System.currentTimeMillis());
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(random.nextInt(100)));
        platformOverview.setTotalHttpRequest(String.valueOf(random.nextInt(10000)));
        platformOverview.setTotalMicroService(String.valueOf(random.nextInt(1000)));
        platformOverview.setTotalNode(String.valueOf(random.nextInt(10)));
        return JsonUtil.toJson(platformOverview);
    }


    protected String getMachineStatusData() {
        Random random = new Random(System.currentTimeMillis());
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(random.nextInt(100)));
        platformOverview.setTotalHttpRequest(String.valueOf(random.nextInt(10000)));
        platformOverview.setTotalMicroService(String.valueOf(random.nextInt(1000)));
        platformOverview.setTotalNode(String.valueOf(random.nextInt(10)));
        return JsonUtil.toJson(platformOverview);
    }

    protected String getMicroServiceStatusData() {
        Random random = new Random(System.currentTimeMillis());
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(random.nextInt(100)));
        platformOverview.setTotalHttpRequest(String.valueOf(random.nextInt(10000)));
        platformOverview.setTotalMicroService(String.valueOf(random.nextInt(1000)));
        platformOverview.setTotalNode(String.valueOf(random.nextInt(10)));
        return JsonUtil.toJson(platformOverview);
    }

    protected String getMachineListData() {
        Random random = new Random(System.currentTimeMillis());
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(random.nextInt(100)));
        platformOverview.setTotalHttpRequest(String.valueOf(random.nextInt(10000)));
        platformOverview.setTotalMicroService(String.valueOf(random.nextInt(1000)));
        platformOverview.setTotalNode(String.valueOf(random.nextInt(10)));
        return JsonUtil.toJson(platformOverview);
    }

    protected String getExceptionData() {
        Random random = new Random(System.currentTimeMillis());
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(random.nextInt(100)));
        platformOverview.setTotalHttpRequest(String.valueOf(random.nextInt(10000)));
        platformOverview.setTotalMicroService(String.valueOf(random.nextInt(1000)));
        platformOverview.setTotalNode(String.valueOf(random.nextInt(10)));
        return JsonUtil.toJson(platformOverview);
    }

}
