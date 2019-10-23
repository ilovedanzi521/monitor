package com.win.dfas.monitor.web.controller;

import com.win.dfas.common.vo.WinResponseData;
import com.win.dfas.monitor.common.constant.LineColorEnum;
import com.win.dfas.monitor.common.entity.DcDevcie;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.vo.*;
import com.win.dfas.monitor.engine.service.IDcDevcieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包名称：com.win.dfas.monitor.web.controller
 * 类名称：MachineDetailController
 * 类描述：监控平台机器明细页面交互接口
 * 创建人：@author lj
 * 创建时间：2019-08-07/13:31
 */
@RestController
@RequestMapping("/machineDetail")
@Api(tags = {"监控平台机器明细页面交互接口"})
public class MachineDetailController extends BaseController {

    @Autowired
    private IDcDevcieService devcieService;

    /**
     * 查询cpu使用率
     */
    @ApiOperation(value = "查询cpu使用率", notes = "查询cpu使用率")
    @PostMapping("/cpuUsage")
    public WinResponseData cpuUsage(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        DcDevcie dcDevcie = devcieService.selectDcDevcieByIp(map.get("ipAddress"));
        MachineCPUUsageVO machineCPUUsageVO = getCpuUsageData(dcDevcie);
        return WinResponseData.handleSuccess(machineCPUUsageVO);
    }

    /**
     * 查询memory使用率
     */
    @ApiOperation(value = "查询memory使用率", notes = "查询memory使用率")
    @PostMapping("/memoryUsage")
    public WinResponseData memoryUsage(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        DcDevcie dcDevcie = devcieService.selectDcDevcieByIp(map.get("ipAddress"));
        MachineCPUUsageVO machineCPUUsageVO = getMemoryUsageData(dcDevcie);
        return WinResponseData.handleSuccess(machineCPUUsageVO);
    }

    /**
     * 查询节点信息
     */
    @ApiOperation(value = "查询节点信息", notes = "查询节点信息")
    @PostMapping("/nodeBaseInfo")
    public WinResponseData nodeBaseInfo(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        DcDevcie dcDevcie = devcieService.selectDcDevcieByIp(map.get("ipAddress"));
        Map<String,String> m = getNodeBaseInfoData(dcDevcie);
        return WinResponseData.handleSuccess(m);
    }

    /**
     * cpu使用变化率
     */
    @ApiOperation(value = "cpu使用变化率", notes = "cpu使用变化率")
    @PostMapping("/cpuLineUsage")
    public WinResponseData cpuLineUsage(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        String ipAddress = map.get("ipAddress");
        CpuLineChartVO cpuLineChartVO = devcieService.getCpuLineChartData(ipAddress);
        return WinResponseData.handleSuccess(cpuLineChartVO);
    }

    /**
     * 磁盘使用占比
     */
    @ApiOperation(value = "磁盘使用占比", notes = "磁盘使用占比")
    @PostMapping("/diskUsage")
    public WinResponseData diskUsage(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        String ipAddress = map.get("ipAddress");
        DiskBarChartVO diskBarChartVO = devcieService.getDiskBarChartData(ipAddress);
        return WinResponseData.handleSuccess(diskBarChartVO);
    }


    private Map<String,String> getNodeBaseInfoData(DcDevcie dcDevcie) {
        Map<String,String> nodeBaseInfo = new HashMap<>();
        nodeBaseInfo.put("balance",dcDevcie.getBalance());
        nodeBaseInfo.put("cpuInfo",dcDevcie.getCpu());
        nodeBaseInfo.put("diskInfo",dcDevcie.getDisk());
        nodeBaseInfo.put("memoryInfo",dcDevcie.getMemory());
        return nodeBaseInfo ;
    }


    private MachineCPUUsageVO getMemoryUsageData(DcDevcie dcDevcie){
        MachineCPUUsageVO machineCPUUsageVO = new MachineCPUUsageVO();
        List<String> legendData=new ArrayList<>();
        List<String> colorData=new ArrayList<>();
        List<Map<String,Object>> seriesData=new ArrayList<>();
        legendData.add("已使用");
        legendData.add("未使用");
        Map<String,Object> cpuMap1 = new HashMap<>();
        cpuMap1.put("name","已使用");
        cpuMap1.put("value",Double.parseDouble(getVal(dcDevcie.getMemory())));
        Map<String,Object> cpuMap2 = new HashMap<>();
        cpuMap2.put("name","未使用");
        cpuMap2.put("value",100 - Double.parseDouble(getVal(dcDevcie.getMemory())));
        seriesData.add(cpuMap1);
        seriesData.add(cpuMap2);
        colorData.add(LineColorEnum.values()[0].getColor());
        colorData.add(LineColorEnum.values()[1].getColor());
        machineCPUUsageVO.setLegendData(legendData);
        machineCPUUsageVO.setSeriesData(seriesData);
        machineCPUUsageVO.setColorData(colorData);
        return machineCPUUsageVO;
    }

    private MachineCPUUsageVO getCpuUsageData(DcDevcie dcDevcie){
        MachineCPUUsageVO machineCPUUsageVO = new MachineCPUUsageVO();
        List<String> legendData=new ArrayList<>();
        List<String> colorData=new ArrayList<>();
        List<Map<String,Object>> seriesData=new ArrayList<>();
        legendData.add("已使用");
        legendData.add("未使用");
        Map<String,Object> cpuMap1 = new HashMap<>();
        cpuMap1.put("name","已使用");
        cpuMap1.put("value",Double.parseDouble(getVal(dcDevcie.getCpu())));
        Map<String,Object> cpuMap2 = new HashMap<>();
        cpuMap2.put("name","未使用");
        cpuMap2.put("value",100 - Double.parseDouble(getVal(dcDevcie.getCpu())));
        seriesData.add(cpuMap1);
        seriesData.add(cpuMap2);
        colorData.add(LineColorEnum.values()[0].getColor());
        colorData.add(LineColorEnum.values()[1].getColor());
        machineCPUUsageVO.setLegendData(legendData);
        machineCPUUsageVO.setSeriesData(seriesData);
        machineCPUUsageVO.setColorData(colorData);
        return machineCPUUsageVO;
    }

    private String getVal(String data) {
        return data.replaceAll("[\\s*|TB|GB|MB|KB|bytes]+", "") ;
    }
}
