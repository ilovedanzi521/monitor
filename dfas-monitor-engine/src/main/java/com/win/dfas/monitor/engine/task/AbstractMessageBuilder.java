package com.win.dfas.monitor.engine.task;

import com.win.dfas.monitor.common.constant.HomeModuleEnum;
import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.SpringContextUtils;
import com.win.dfas.monitor.common.vo.*;
import com.win.dfas.monitor.engine.service.IMachineService;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocket;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocketManager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class AbstractMessageBuilder {

    /** 千分位格式化 */
    protected NumberFormat thousandBitNumberFormat = NumberFormat.getNumberInstance();

    public void push(HomeModuleEnum homeModuleEnum, String messageData) {
        CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = AbstractWebSocketManager.instance().get(homeModuleEnum);
        if (webSocketSet != null) {
            System.out.println("客户端连接个数：" + webSocketSet.size());
            for (AbstractWebSocket webSocket : webSocketSet) {
                try {
                    webSocket.sendMessage(messageData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 构造平台概览模块数据
     * @return
     */
    protected String getPlatformOverviewData() {
        IMachineService dcDevcieService = SpringContextUtils.getBean(IMachineService.class);
        Random random = new Random(System.currentTimeMillis());
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(thousandBitNumberFormat.format(random.nextInt(100) + 1)));
        platformOverview.setTotalHttpRequest(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10000) + 1)));
        platformOverview.setTotalMicroService(String.valueOf(thousandBitNumberFormat.format(random.nextInt(1000) + 1)));
        platformOverview.setTotalNode(String.valueOf(dcDevcieService.getTotalNode()));
        return JsonUtil.toJson(platformOverview);
    }

    /**
     * 构造Qps模块数据
     * @return
     */
    protected String getQpsData() {
        Random random = new Random(System.currentTimeMillis());
        QpsVO qps = new QpsVO();

        int hour = Integer.parseInt(DateUtils.getCurrentHour());

        List<String> xAxisData = new ArrayList<>();
        List<Double> yAxisData = new ArrayList<>();

        String suffix = ":00";

        for (int i = 0; i <= hour + 1; i++) {
            if (i < 10) {
                xAxisData.add("0" + i + suffix);
            } else {
                xAxisData.add(i + suffix);
            }
            yAxisData.add(random.nextDouble());
        }
        qps.setXAxisData(xAxisData);
        qps.setYAxisData(yAxisData);

        return JsonUtil.toJson(qps);
    }


    /**
     * 构造机器状态模块数据
     * @return
     */
    protected String getMachineStatusData() {
        Random random = new Random(System.currentTimeMillis());
        List<MachineStatusVO> machineStatusList=new ArrayList<>();
        int count=20+random.nextInt(10);
        for(int i=0;i<count;i++){
            MachineStatusVO machineStatus =new MachineStatusVO();
            machineStatus.setId(String.valueOf(i));
            machineStatus.setIpAddress("192.168.0."+random.nextInt(255));
            machineStatus.setCpuPer(String.valueOf(random.nextInt(100)) + "." + String.valueOf(random.nextInt(100)) + "%");
            machineStatus.setCpuNum(random.nextInt(5));
            machineStatus.setDiskPer(String.valueOf(random.nextInt(100)) + "." + String.valueOf(random.nextInt(100)) + "%");
            machineStatus.setDiskSize(String.valueOf(random.nextInt(1000)) );
            machineStatus.setMemoryPer(String.valueOf(random.nextInt(100)) + "." + String.valueOf(random.nextInt(100)) + "%");
            machineStatus.setState(String.valueOf(random.nextInt(4)));
            machineStatus.setMemorySize(String.valueOf(random.nextInt(1000)) );
            machineStatusList.add(machineStatus);
        }
        return JsonUtil.toJson(machineStatusList);
    }

    /**
     * 构造微服务状态模块数据
     * @return
     */
    protected String getMicroServiceStatusData() {
        Random random = new Random(System.currentTimeMillis());
        List<MicroServiceStateVO> microServiceStatusList=new ArrayList<>();
        int count=70+random.nextInt(10);
        for(int i=0;i<count;i++){
            MicroServiceStateVO microServiceStatus =new MicroServiceStateVO();
            microServiceStatus.setId(String.valueOf(i));
            microServiceStatus.setMicroServiceName("订单服务");
            microServiceStatus.setWarn(random.nextInt(500));
            microServiceStatus.setError(random.nextInt(5000));
            microServiceStatus.setState(String.valueOf(random.nextInt(4)));
            microServiceStatusList.add(microServiceStatus);
        }
        return JsonUtil.toJson(microServiceStatusList);
    }

    /**
     * 构造机器列表模块数据
     * @return
     */
    protected String getMachineListData() {
        Random random = new Random(System.currentTimeMillis());
        List<MachineVO> machineList = new ArrayList<>();
        for (int i = 0; i < random.nextInt(10) + 1; i++) {
            MachineVO machine = new MachineVO();
            machine.setIp("192.168.0." + random.nextInt(255));
            machine.setState(String.valueOf(random.nextInt(2)));
            machine.setBalance(String.valueOf(random.nextInt(100)) + "." + String.valueOf(random.nextInt(100)) + "%");
            machine.setCpu(String.valueOf(random.nextInt(100)) + "." + String.valueOf(random.nextInt(100)) + "%");
            machine.setMemory(String.valueOf(random.nextInt(100)) + "." + String.valueOf(random.nextInt(100)) + "%");
            machine.setDisk(String.valueOf(random.nextInt(100)) + "." + String.valueOf(random.nextInt(100)) + "%");
            machineList.add(machine);
        }
        return JsonUtil.toJson(machineList);
    }

    /**
     * 构造异常模块数据
     * @return
     */
    protected String getExceptionData() {
        Random random = new Random(System.currentTimeMillis());
        ExceptionVO exception = new ExceptionVO();
        exception.setMachineCpu(String.valueOf(thousandBitNumberFormat.format(random.nextInt(100))));
        exception.setMachineMemory(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10000))));
        exception.setMachineDisk(String.valueOf(thousandBitNumberFormat.format(random.nextInt(1000))));
        exception.setMicroServiceMemory(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10))));
        exception.setMicroServiceWarnLog(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10))));
        exception.setMicroServiceErrorLog(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10))));
        return JsonUtil.toJson(exception);
    }

}
