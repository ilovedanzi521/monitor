package com.win.dfas.monitor.web.controller;

import com.win.dfas.monitor.engine.service.PrometheusService;
import com.win.dfas.monitor.exporter.microservice.metrics.MonitorMetrics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名称：com.win.dfas.monitor.web.controller
 * 类名称：IndexController
 * 类描述：监控平台主控面板
 * 创建人：@author wangyaoheng
 * 创建时间：2019-08-07/13:31
 */
@RestController
@RequestMapping("/index")
@Api(tags = {"监控平台主控面板交互接口"})
public class IndexController extends BaseController {

    @Autowired
    private PrometheusService monitorService;

    /**
     * 获取qps
     */
    @MonitorMetrics
    @ApiOperation(value = "查询平台Qps", notes = "查询平台Qps")
    @GetMapping("/httpRequestQps")
    public String httpRequestQps() {
        return monitorService.getQps();
    }

    /**
     * 查询平台当日请求数
     */
    @MonitorMetrics
    @ApiOperation(value = "查询平台当日请求数", notes = "查询平台当日请求数")
    @GetMapping("/httpRequestTotal")
    public String httpRequestTotal() {
        return monitorService.getHttpRequestTotal();
    }

    /**
     * 查询平台微服务列表
     * @return
     */
    @MonitorMetrics
    @ApiOperation(value = "查询平台微服务列表", notes = "查询平台微服务列表")
    @GetMapping("/getMicroServiceList")
    public String getMicroServiceList() {
        return "";
    }

}
