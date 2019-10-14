package com.win.dfas.monitor.web.controller;

import com.win.dfas.common.vo.WinResponseData;
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
    @GetMapping("/insertMicroService")
    public WinResponseData insertMicroService(@RequestBody MicroServiceReqVO reqVO) {
        monitorService.insertMicroService(reqVO);
        return WinResponseData.handleSuccess("success");
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

}
