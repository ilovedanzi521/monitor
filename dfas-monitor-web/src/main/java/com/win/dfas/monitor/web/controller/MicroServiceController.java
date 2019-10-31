package com.win.dfas.monitor.web.controller;

import com.github.pagehelper.PageInfo;
import com.win.dfas.common.vo.WinResponseData;
import com.win.dfas.monitor.common.constant.ReturnMsgEnum;
import com.win.dfas.monitor.common.vo.MicroServiceRepVO;
import com.win.dfas.monitor.common.vo.MicroServiceReqVO;
import com.win.dfas.monitor.engine.service.MicroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 包名称：com.win.dfas.monitor.web.controller
 * 类名称：MicroServiceController
 * 类描述：监控平台微服务交互接口
 * 创建人：@author wangyaoheng
 * 创建时间：2019-08-07/13:31
 */
@RestController
@RequestMapping("/microService")
@Api(tags = {"监控平台微服务交互接口"})
public class MicroServiceController extends BaseController {

    @Autowired
    private MicroService microService;

    @GetMapping("/test")
    public WinResponseData test() {
        return WinResponseData.handleSuccess(ReturnMsgEnum.Default.getMsg());
    }

    /**
     * 新增微服务
     */
    @ApiOperation(value = "新增微服务", notes = "新增微服务")
    @PostMapping("/insertMicroService")
    public WinResponseData insertMicroService(@RequestBody MicroServiceReqVO reqVO) {
        microService.insertMicroService(reqVO);
        return WinResponseData.handleSuccess(ReturnMsgEnum.Add.getMsg());
    }

    /**
     * 同步微服务
     */
    @ApiOperation(value = "同步微服务", notes = "同步微服务")
    @PutMapping("/synchronizeMicroService")
    public WinResponseData synchronizeMicroService() {
        microService.synchronizeMicroService();
        return WinResponseData.handleSuccess(ReturnMsgEnum.Syn.getMsg());
    }


    /**
     * 查询单个微服务
     */
    @ApiOperation(value = "查询单个微服务", notes = "查询单个微服务")
    @PostMapping("/microServiceInfo")
    public WinResponseData microServiceInfo(@RequestBody MicroServiceReqVO reqVO) {
        return WinResponseData.handleSuccess(microService.microServiceInfo(reqVO));
    }

    /**
     * 查询微服务JVM变化情况
     */
    @ApiOperation(value = "查询微服务JVM变化情况", notes = "查询微服务JVM变化情况")
    @PostMapping("/jvmMemory")
    public WinResponseData jvmMemory(@RequestBody MicroServiceReqVO reqVO) {
        return WinResponseData.handleSuccess(microService.jvmMemory(reqVO));
    }

    /**
     * 更新微服务
     */
    @ApiOperation(value = "更新微服务", notes = "更新微服务")
    @PutMapping("/updateMicroService")
    public WinResponseData updateMicroService(@RequestBody MicroServiceReqVO reqVO) {
        microService.updateMicroService(reqVO);
        return WinResponseData.handleSuccess(ReturnMsgEnum.Edit.getMsg());
    }

    /**
     * 删除微服务
     */
    //@MonitorMetrics
    @ApiOperation(value = "删除微服务", notes = "删除微服务")
    @DeleteMapping("/deleteMicroService/{id}")
    public WinResponseData deleteMicroService(@PathVariable("id") Long id) {
        microService.deleteMicroService(id);
        return WinResponseData.handleSuccess(ReturnMsgEnum.Remove.getMsg());
    }

    /**
     * 批量删除微服务
     */
    //@MonitorMetrics
    @ApiOperation(value = "批量删除微服务", notes = "批量删除微服务")
    @DeleteMapping("/batDeleteMicroService/{ids}")
    public WinResponseData batDeleteMicroService(@PathVariable("ids") String ids) {
        microService.deleteMicroServiceByIds(ids);
        return WinResponseData.handleSuccess(ReturnMsgEnum.Remove.getMsg());
    }

    /**
     * 查询平台微服务列表
     * @return
     */
    //@MonitorMetrics
    @ApiOperation(value = "查询平台微服务列表", notes = "查询平台微服务列表")
    @PostMapping("/getMicroServiceList")
    public WinResponseData getMicroServiceList(@RequestBody MicroServiceReqVO reqVO) {
        PageInfo<MicroServiceRepVO> pageInfo = microService.getMicroServiceList(reqVO);
        return WinResponseData.handleSuccess(pageInfo);
    }

    /**
     * 微服务搜索查询
     * @return
     */
    // @MonitorMetrics
    @ApiOperation(value = "微服务搜索查询", notes = "微服务搜索查询")
    @PostMapping("/searchMicroService")
    public WinResponseData searchMicroService(@RequestBody MicroServiceReqVO reqVO) {
        return WinResponseData.handleSuccess(microService.searchMicroService(reqVO));
    }

    /**
     * 微服务面板查询
     * @return
     */
    //@MonitorMetrics
    @ApiOperation(value = "微服务面板查询", notes = "微服务面板查询")
    @PostMapping("/microServicePanel")
    public WinResponseData microServicePanel(@RequestBody MicroServiceReqVO reqVO) {
        return WinResponseData.handleSuccess(microService.microServicePanel(reqVO));
    }


    /**
     * 微服务机器数据查询
     * @return
     */
    //@MonitorMetrics
    @ApiOperation(value = "微服务机器数据", notes = "微服务机器数据")
    @PostMapping("/microServiceMachineList")
    public WinResponseData microServiceMachineList(@RequestBody MicroServiceReqVO reqVO) {
        return WinResponseData.handleSuccess(microService.microServiceMachineList(reqVO));
    }

}
