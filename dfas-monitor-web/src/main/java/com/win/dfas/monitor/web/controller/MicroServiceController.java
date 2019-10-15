package com.win.dfas.monitor.web.controller;

import com.win.dfas.common.vo.WinResponseData;
import com.win.dfas.monitor.common.constant.ReturnMsgEnum;
import com.win.dfas.monitor.common.vo.MicroServiceReqVO;
import com.win.dfas.monitor.engine.service.MonitorService;
import com.win.dfas.monitor.exporter.microservice.metrics.MonitorMetrics;
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
    private MonitorService monitorService;

    /**
     * 新增微服务
     */
    @MonitorMetrics
    @ApiOperation(value = "新增微服务", notes = "新增微服务")
    @PostMapping("/insertMicroService")
    public WinResponseData insertMicroService(@RequestBody MicroServiceReqVO reqVO) {
        monitorService.insertMicroService(reqVO);
        return WinResponseData.handleSuccess(ReturnMsgEnum.Add.getMsg());
    }

    /**
     * 同步微服务
     */
    @MonitorMetrics
    @ApiOperation(value = "同步微服务", notes = "同步微服务")
    @PutMapping("/synchronizeMicroService")
    public WinResponseData synchronizeMicroService() {
        monitorService.synchronizeMicroService();
        return WinResponseData.handleSuccess(ReturnMsgEnum.Syn.getMsg());
    }

    /**
     * 更新微服务
     */
    @MonitorMetrics
    @ApiOperation(value = "更新微服务", notes = "更新微服务")
    @PutMapping("/updateMicroService")
    public WinResponseData  updateMicroService(@RequestBody MicroServiceReqVO reqVO) {
        monitorService.updateMicroService(reqVO);
        return WinResponseData.handleSuccess(ReturnMsgEnum.Edit.getMsg());
    }

    /**
     * 删除微服务
     */
    @MonitorMetrics
    @ApiOperation(value = "删除微服务", notes = "删除微服务")
    @DeleteMapping("/deleteMicroService/{id}")
    public WinResponseData  deleteMicroService(@PathVariable("id") String id) {
        monitorService.deleteMicroService(id);
        return WinResponseData.handleSuccess(ReturnMsgEnum.Remove.getMsg());
    }

    /**
     * 批量删除微服务
     */
    @MonitorMetrics
    @ApiOperation(value = "批量删除微服务", notes = "批量删除微服务")
    @DeleteMapping("/batDeleteMicroService/{ids}")
    public WinResponseData  batDeleteMicroService(@PathVariable("ids") String ids) {
        monitorService.deleteMicroServiceByIds(ids);
        return WinResponseData.handleSuccess(ReturnMsgEnum.Remove.getMsg());
    }

    /**
     * 查询平台微服务列表
     * @return
     */
    @MonitorMetrics
    @ApiOperation(value = "查询平台微服务列表", notes = "查询平台微服务列表")
    @PostMapping("/getMicroServiceList")
    public WinResponseData getMicroServiceList(@RequestBody MicroServiceReqVO reqVO) {
        return WinResponseData.handleSuccess(monitorService.getMicroServiceList(reqVO));
    }

    /**
     * 微服务搜索查询
     * @return
     */
    @MonitorMetrics
    @ApiOperation(value = "微服务搜索查询", notes = "微服务搜索查询")
    @PostMapping("/searchMicroService")
    public WinResponseData searchMicroService(@RequestBody MicroServiceReqVO reqVO) {
        return WinResponseData.handleSuccess(monitorService.searchMicroService(reqVO));
    }



}
