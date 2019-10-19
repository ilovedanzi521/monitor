package com.win.dfas.monitor.web.controller;

import com.win.dfas.monitor.common.entity.DcDevcie;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.StringUtils;
import com.win.dfas.monitor.common.util.id.IDUtils;
import com.win.dfas.monitor.common.vo.MachineStatusVO;
import com.win.dfas.monitor.common.vo.MachineVO;
import com.win.dfas.monitor.engine.service.IDcDevcieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包名称：com.win.dfas.monitor.web.controller
 * 类名称：MachineController
 * 类描述：机器控制类
 * 创建人：@author lj
 * 创建时间：2019-10-10/13:31
 */
@RestController
@RequestMapping("/machine")
@Api(tags = {"机器信息交互接口"})
public class MachineController extends BaseController {

    @Autowired
    private IDcDevcieService dcDevcieService;

    /** 新增机器 */
    @ApiOperation(value = "新增机器", notes = "新增机器")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "机器信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/add")
    public String add(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        DcDevcie dcDevcie = new DcDevcie();
        dcDevcie.setId(IDUtils.nextId());
        dcDevcie.setIpAddress(map.get("ipAddress"));
        dcDevcie.setName(map.get("name"));
        dcDevcieService.insertDcDevcie(dcDevcie);
        return successData(dcDevcie.getId(),"新增机器成功");
    }

    /** 修改机器 */
    @ApiOperation(value = "修改机器", notes = "修改机器")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "机器信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/modify")
    public String modify(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        DcDevcie dcDevcie = new DcDevcie();
        dcDevcie.setId(map.get("id"));
        dcDevcie.setIpAddress(map.get("ipAddress"));
        dcDevcie.setName(map.get("name"));
        dcDevcieService.updateDcDevcie(dcDevcie);
        return successData(dcDevcie.getId(),"修改机器成功");
    }

    /** 修改机器 */
    @ApiOperation(value = "删除机器", notes = "删除机器")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "机器信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/delete")
    public String delete(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        String id = map.get("id") ;
        dcDevcieService.deleteDcDevcieByIds(id);
        return successData(id,"删除机器成功");
    }
    /** 修改机器 */
    @ApiOperation(value = "批量删除机器", notes = "批量删除机器")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "机器信息", required = true, paramType = "body", dataType = "String")})
    @DeleteMapping("/batchDelete/{ids}")
    public String batchDelete(@PathVariable("ids") String ids) {
        dcDevcieService.deleteDcDevcieByIds(ids);
        return successData(ids,"删除机器成功");
    }

    /** 一键同步 */
    @ApiOperation(value = "一键同步", notes = "一键同步")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "一键同步", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/onKeySync")
    public String onKeySync(@RequestBody String data) {
        DcDevcie dcDevcie = new DcDevcie();
        dcDevcie.setId(IDUtils.nextId());
        dcDevcie.setIpAddress("test_onKeySync_ip");
        dcDevcie.setName("test_onKeySync_name");
        dcDevcieService.insertDcDevcie(dcDevcie);
        return successData(dcDevcie.getId(),"一键同步成功");
    }



    /** 机器列表 */
    @ApiOperation(value = "机器列表", notes = "机器列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "机器列表", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/machinePage")
    public String machinePage(@RequestBody String data) {
        DcDevcie dcDevcie = new DcDevcie();
        List<DcDevcie> dcDevices = dcDevcieService.selectDcDevcieList(dcDevcie);
        Map<String,Object> result = new HashMap<>();
        result.put("total",dcDevices.size());
        result.put("list",dcDevices);
       return successData(result,"操作成功");
    }

    /** 机器明细面板数据 */
    @ApiOperation(value = "机器列表机器明细面板数据", notes = "机器明细面板数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "机器明细面板数据", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/machinePanelData")
    public String machinePanelData(@RequestBody String data) {
        return successData(machinePanelData(),"操作成功");
    }
    private List<MachineStatusVO> machinePanelData() {
        DcDevcie dcDevcie = new DcDevcie();
        List<DcDevcie> dcDevices = dcDevcieService.selectDcDevcieList(dcDevcie);
        List<MachineStatusVO> machineStatusList=new ArrayList<>();
        for (DcDevcie dc : dcDevices) {
            MachineStatusVO machineStatus =new MachineStatusVO();
            machineStatus.setId(dc.getId());
            machineStatus.setIpAddress(dc.getIpAddress());
            machineStatus.setCpuPer(formatValue(dc.getCpu(),"%"));
            machineStatus.setCpuNum(dc.getCpuNum());
            machineStatus.setDiskPer(formatValue(dc.getCpu(),"%"));
            machineStatus.setDiskSize(formatValue(dc.getDiskSize(),""));
            machineStatus.setMemoryPer(formatValue(dc.getMemory(),""));
            machineStatus.setState(formatValue(String.valueOf(dc.getStatus()),""));
            machineStatus.setMemorySize(formatValue(dc.getMemorySize(),""));
            machineStatusList.add(machineStatus);
        }
        return machineStatusList;
    }

    /** 首页前5条机器数据 */
    @ApiOperation(value = "首页前5条机器数据", notes = "首页前5条机器数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "首页前5条机器数据", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/homePageMachineTop5")
    public String HomePageMachineTop5(@RequestBody String data) {
        List<MachineVO> machineList = new ArrayList<>();
        DcDevcie dcDevcie = new DcDevcie();
        List<DcDevcie> dcDevices = dcDevcieService.selectDcDevcieList(dcDevcie);
        for (DcDevcie dc : dcDevices){
            MachineVO machine = new MachineVO();
            machine.setIp(dc.getIpAddress());
            machine.setState(getStatus(dc));
            machine.setBalance(formatValue(dc.getBalance(),"%"));
            machine.setCpu(formatValue(dc.getCpu(),"%"));
            machine.setMemory(formatValue(dc.getMemory(),"%"));
            machine.setDisk(formatValue(dc.getDisk(),"%"));
            machineList.add(machine);
        }
        return successData(machineList,"操作成功");
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

    private String formatValue(String val,String format){
        if(StringUtils.isNotEmpty(val)){
            return val + format;
        }
        return "-" ;
    }


}
