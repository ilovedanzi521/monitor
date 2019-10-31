package com.win.dfas.monitor.engine.task;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import com.win.dfas.monitor.common.constant.HomeModuleEnum;
import com.win.dfas.monitor.common.constant.ResultTypeEnum;
import com.win.dfas.monitor.common.entity.DcDevcie;
import com.win.dfas.monitor.common.util.BigDecimalUtils;
import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.util.StringUtils;
import com.win.dfas.monitor.common.vo.MachineStatusVO;
import com.win.dfas.monitor.common.vo.MachineVO;
import com.win.dfas.monitor.common.vo.MetricsResultVO;
import com.win.dfas.monitor.common.vo.MetricsReturnMsgVO;
import com.win.dfas.monitor.engine.service.IDcDevcieService;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocket;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocketManager;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务器定时推送任务
 * </p>
 *
 * @author: lj

 */
@Slf4j
//@Component
public class MachineTask {

    @Value("${prometheus.server.url}")
    private String prometheusServerUrl;

    @Autowired
    private IDcDevcieService dcDevcieService;

    /**
     * 按照标准时间来算，每隔 10s 执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void pushMachineStatusData() throws Exception {
        log.info("【推送机器数据】开始执行：{}", DateUtils.getCurrentDateTime());
        CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = AbstractWebSocketManager.instance().get(HomeModuleEnum.machineState);
        if(webSocketSet != null){
            for (AbstractWebSocket webSocket : webSocketSet) {
                try {
                    webSocket.sendMessage(getMachineStatusData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log.info("【推送机器数据】执行结束：{}", DateUtils.getCurrentDateTime());
        }
    }

    /**
     * 按照标准时间来算，每隔 10s 执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void pushMachineListData() throws Exception {
        log.info("【推送机器数据】开始执行：{}", DateUtils.getCurrentDateTime());
        CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = AbstractWebSocketManager.instance().get(HomeModuleEnum.machineList);
        if(webSocketSet != null){
            for (AbstractWebSocket webSocket : webSocketSet) {
                try {
                    webSocket.sendMessage(getMachineListData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log.info("【推送机器数据】执行结束：{}", DateUtils.getCurrentDateTime());
        }
    }

    /**
     * 按照标准时间来算，每隔 10s 执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void syncMachineStatusData() throws Exception {
        log.info("【同步机器数据】开始执行：{}", DateUtils.getCurrentDateTime());
        DcDevcie dcDevcie = new DcDevcie();
        List<DcDevcie> dcDevices = dcDevcieService.selectDcDevcieList(dcDevcie);
        syncMachineData(dcDevices);
        dcDevcieService.updateBatch(dcDevices);
        log.info("【同步机器数据】执行结束：{}", DateUtils.getCurrentDateTime());
    }

    private String getMachineStatusData() {
        DcDevcie dcDevcie = new DcDevcie();
        List<DcDevcie> dcDevices = dcDevcieService.selectDcDevcieList(dcDevcie);
        List<MachineStatusVO> machineStatusList = new ArrayList<>();
        for (DcDevcie dc : dcDevices) {
            MachineStatusVO machineStatus = new MachineStatusVO();
            machineStatus.setId(dc.getId());
            machineStatus.setIpAddress(dc.getIpAddress());
            machineStatus.setCpuPer(formatValue(dc.getCpu(),"%"));
            machineStatus.setCpuNum(dc.getCpuNum());
            machineStatus.setDiskPer(formatValue(dc.getDisk(),"%"));
            machineStatus.setDiskSize(formatValue(dc.getDiskSize(),""));
            machineStatus.setMemoryPer(formatValue(dc.getMemory(),"%"));
            machineStatus.setState(formatValue(String.valueOf(dc.getStatus()),""));
            machineStatus.setMemorySize(formatValue(dc.getMemorySize(),""));
            machineStatusList.add(machineStatus);
        }
        return JsonUtil.toJson(machineStatusList);
    }

    /**
     * 构造机器列表模块数据
     * @return
     */
    private String getMachineListData() {
        Random random = new Random(System.currentTimeMillis());
        DcDevcie dcDevcie = new DcDevcie();
        List<DcDevcie> dcDevices = dcDevcieService.selectDcDevcieList(dcDevcie);
        List<MachineStatusVO> machineStatusList = new ArrayList<>();
        List<MachineVO> machineList = new ArrayList<>();
        for (DcDevcie dc : dcDevices) {
            MachineVO machine = new MachineVO();
            machine.setIp(dc.getIpAddress());
            machine.setState(getStatus(dc));
            machine.setBalance(formatValue(dc.getBalance(),"%"));
            machine.setCpu(formatValue(dc.getCpu(),"%"));
            machine.setMemory(formatValue(dc.getMemory(),"%"));
            machine.setDisk(formatValue(dc.getDisk(),"%"));
            String cpuInfo = "核数：" + dc.getCpuCore() + " 使用率：" + dc.getCpu() + "%" ;
            machine.setCpuInfo(cpuInfo);
            machine.setDiskInfo(dc.getDisk()+ "%");
            machine.setMemoryInfo(dc.getMemory()+ "%");
            machine.setBalanceInfo(dc.getBalance()+ "%");
            machineList.add(machine);
        }
        return JsonUtil.toJson(machineList);
    }


    /**
     * 同步机器状态、cpu使用率、内存使用率、磁盘使用率、CPU核数、磁盘大小、内存大小、机器负载
     *
     */
    private void syncMachineData(List<DcDevcie> dcDevices) {
        for (DcDevcie dc : dcDevices) {
            String ip = dc.getIpAddress();
            Map<String, Object> parameters = new HashMap<>();
            //机器状态
            parameters.put("queryParam", "up{instance='expoter_" + ip + "'}");
            List<Object> value = getValue(prometheusServerUrl, parameters);
            if (value != null && value.size() > 0) {
                String status = value.get(1).toString();
                if (1 == Integer.parseInt(status)) {
                    /*
                     * 0 - 离线
                     * 1 - 异常
                     * 2 - 告警
                     * 3 - 在线
                     * */
                    dc.setStatus(3);
                } else {
                    dc.setStatus(Integer.parseInt(status));
                }
            }

            //未连接状态cpu使用率、内存使用率、磁盘使用率设置为0
            if (dc.getStatus() == null || dc.getStatus() == 0) {
                dc.setCpu("");
                dc.setMemory("");
                dc.setDisk("");
                dc.setCpuNum(0);
                dc.setDiskSize("");
                dc.setMemorySize("");
                //连接状态实时采集cpu使用率、内存使用率、磁盘使用率数据、CPU核数、磁盘大小、内存大小
            } else {
                //cpu使用率
                parameters.put("queryParam", "100 - (avg(irate(node_cpu_seconds_total{instance='expoter_" + ip + "',mode='idle'}[5m])) * 100)");
                value = getValue(prometheusServerUrl, parameters);
                if (value != null && value.size() > 0) {
                    String cpu = value.get(1).toString();
                    dc.setCpu(String.valueOf(BigDecimalUtils.bigDecimalStringRound(cpu, 2)));
                }

                //内存使用率
                parameters.put("queryParam", "(1 - (node_memory_MemAvailable_bytes{instance='expoter_" + ip + "'} / (node_memory_MemTotal_bytes{instance='expoter_" + ip + "'})))* 100");
                value = getValue(prometheusServerUrl, parameters);
                if (value != null && value.size() > 0) {
                    String memory = value.get(1).toString();
                    dc.setMemory(String.valueOf(BigDecimalUtils.bigDecimalStringRound(memory, 2)));
                }

                //磁盘使用率
                parameters.put("queryParam", "100 - node_filesystem_free_bytes{instance='expoter_" + ip + "',fstype!~\"rootfs|selinuxfs|autofs|rpc_pipefs|tmpfs|udev|none|devpts|sysfs|debugfs|fuse.*\"} / node_filesystem_size_bytes{instance='expoter_" + ip + "',fstype!~\"rootfs|selinuxfs|autofs|rpc_pipefs|tmpfs|udev|none|devpts|sysfs|debugfs|fuse.*\"} * 100");
                value = getValue(prometheusServerUrl, parameters);
                if (value != null && value.size() > 0) {
                    String disk = value.get(1).toString();
                    dc.setDisk(String.valueOf(BigDecimalUtils.bigDecimalStringRound(disk, 2)));
                }

                //CPU核数
                parameters.put("queryParam", "count(count(node_cpu_seconds_total{instance='expoter_" + ip + "', mode='system'}) by (cpu))");
                value = getValue(prometheusServerUrl, parameters);
                if (value != null && value.size() > 0) {
                    String cpuNum = value.get(1).toString();
                    dc.setCpuNum(Integer.parseInt(cpuNum));
                }


                //磁盘大小
                parameters.put("queryParam", "node_filesystem_size_bytes{instance='expoter_" + ip + "',fstype=~'ext4|xfs'}");
                value = getValue(prometheusServerUrl, parameters);
                if (value != null && value.size() > 0) {
                    String diskSize = value.get(1).toString();
                    dc.setDiskSize(formatSize(diskSize));
                }

                //内存大小
                parameters.put("queryParam", "node_memory_MemTotal_bytes{instance='expoter_" + ip + "'}");
                value = getValue(prometheusServerUrl, parameters);
                if (value != null && value.size() > 0) {
                    String memorySize = value.get(1).toString();
                    dc.setMemorySize(formatSize(memorySize));
                }

                //机器负载
                parameters.put("queryParam", " node_load5{instance='expoter_" + ip + "'}");
                value = getValue(prometheusServerUrl, parameters);
                if (value != null && value.size() > 0) {
                    String balance = value.get(1).toString();
                    dc.setBalance(balance);
                }

                //cpu核数
                parameters.put("queryParam", " count(count(node_cpu_seconds_total{instance='expoter_"+ip+"', mode='system'}) by (cpu))");
                value = getValue(prometheusServerUrl, parameters);
                if (value != null && value.size() > 0) {
                    String cpuCore = value.get(1).toString();
                    dc.setCpuCore(cpuCore);
                }
            }
        }
    }

    public static String formatSize(String value) {
        long size = Long.parseLong(value);
        String hrSize = null;
        double b = size;
        double k = size / 1024.0;
        double m = ((size / 1024.0) / 1024.0);
        double g = (((size / 1024.0) / 1024.0) / 1024.0);
        double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);
        DecimalFormat dec = new DecimalFormat("0.00");
        if (t > 1) {
            hrSize = dec.format(t).concat(" TB");
        } else if (g > 1) {
            hrSize = dec.format(g).concat(" GB");
        } else if (m > 1) {
            hrSize = dec.format(m).concat(" MB");
        } else if (k > 1) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" bytes");
        }
        return hrSize;
    }

    private String formatValue(String val,String format){
        if(StringUtils.isNotEmpty(val)){
            return val + format;
        }
        return "-" ;
    }
    private List<Object> getValue(String prometheusServerUrl, Map<String, Object> parameters) {
        String url = prometheusServerUrl + "/api/v1/query?query={queryParam}";
        String result = RestfulTools.get(url, String.class, parameters);
        MetricsReturnMsgVO metricsReturnMsgVO = convert(result);
        List<MetricsResultVO> resultList = metricsReturnMsgVO.getData().getResult();
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0).getValue();
        }
        return null;
    }

    private MetricsReturnMsgVO convert(String result) {
        MetricsReturnMsgVO metricsReturnMsgVO = new MetricsReturnMsgVO();
        metricsReturnMsgVO = JsonUtil.toObject(result, MetricsReturnMsgVO.class);
        List<MetricsResultVO> metricsResultList = metricsReturnMsgVO.getData().getResult();
        ResultTypeEnum resultType = metricsReturnMsgVO.getData().getResultType();
        if (metricsResultList != null) {
            for (MetricsResultVO metricsResultVO : metricsResultList) {
                if (resultType == ResultTypeEnum.matrix) {
                    List<Object> valueList = metricsResultVO.getValues();
                    if (valueList != null) {
                        for (Object object : valueList) {
                            List values = (ArrayList) object;
                            values.add(DateUtils.doubleToDate((double) values.get(0)));
                        }
                    }
                } else {
                    List<Object> valueList = metricsResultVO.getValue();
                    valueList.add(DateUtils.doubleToDate((double) valueList.get(0)));
                }
            }
        }
        return metricsReturnMsgVO;
    }

    private String getStatus(DcDevcie dc) {
        String status;
        if(null == dc.getStatus()){
            status = "0";
        }else{
            status = formatValue(String.valueOf(dc.getStatus()),"");
        }
        return status;
    }
}
