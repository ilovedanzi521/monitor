package com.win.dfas.monitor.engine.task;

import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.vo.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractMessageBuilder {

    /** 千分位格式化 */
    protected NumberFormat thousandBitNumberFormat = NumberFormat.getNumberInstance();

    /**
     * 构造平台概览模块数据
     * @return
     */
    protected String getPlatformOverviewData() {
        Random random = new Random(System.currentTimeMillis());
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(thousandBitNumberFormat.format(random.nextInt(100) + 1)));
        ;
        platformOverview.setTotalHttpRequest(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10000) + 1)));
        platformOverview.setTotalMicroService(String.valueOf(thousandBitNumberFormat.format(random.nextInt(1000) + 1)));
        platformOverview.setTotalNode(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10) + 1)));
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
        List<Integer> yAxisData = new ArrayList<>();

        String suffix = ":00";

        for (int i = 0; i <= hour + 1; i++) {
            if (hour < 10) {
                xAxisData.add("0" + i + suffix);
            } else {
                xAxisData.add(i + suffix);
            }
            yAxisData.add(random.nextInt(1000));
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
            machineStatus.setState(String.valueOf(random.nextInt(3)+1));
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
        List<MachineStatusVO> machineStatusList=new ArrayList<>();
        int count=70+random.nextInt(10);
        for(int i=0;i<count;i++){
            MachineStatusVO machineStatus =new MachineStatusVO();
            machineStatus.setId(String.valueOf(i));
            machineStatus.setIpAddress("192.168.0."+random.nextInt(255));
            machineStatus.setCpuPer(String.valueOf(random.nextInt(100)) + "." + String.valueOf(random.nextInt(100)) + "%");
            machineStatus.setCpuNum(random.nextInt(5));
            machineStatus.setDiskPer(String.valueOf(random.nextInt(100)) + "." + String.valueOf(random.nextInt(100)) + "%");
            machineStatus.setDiskSize(String.valueOf(random.nextInt(1000)) );
            machineStatus.setMemoryPer(String.valueOf(random.nextInt(100)) + "." + String.valueOf(random.nextInt(100)) + "%");
            machineStatus.setState(String.valueOf(random.nextInt(3)+1));
            machineStatus.setMemorySize(String.valueOf(random.nextInt(1000)) );
            machineStatusList.add(machineStatus);
        }
        return JsonUtil.toJson(machineStatusList);
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
            machine.setStatus(String.valueOf(random.nextInt(2)));
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
