package com.win.dfas.monitor.web.controller;

import com.win.dfas.common.vo.WinResponseData;
import com.win.dfas.monitor.common.vo.QpsVO;
import com.win.dfas.monitor.engine.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/home")
@Api(tags = {"监控平台主控面板交互接口"})
public class HomeController extends BaseController {

    @Autowired
    private HomeService homeService;

    /**
     * 获取qps
     */
    @ApiOperation(value = "查询平台Qps", notes = "查询平台Qps")
    @PostMapping("/qps")
    public WinResponseData qps() {
        QpsVO qpsVO = homeService.getQpsData();
        return WinResponseData.handleSuccess(qpsVO);
    }

    /**
     * 获取qps图表数据
     */
    @ApiOperation(value = "查询平台Qps图表数据", notes = "查询平台Qps图表数据")
    @PostMapping("/exception")
    public WinResponseData exception() {
        return WinResponseData.handleSuccess(homeService.getExceptionData());
    }


    /**
     * 查询平台当日请求数
     */
    @ApiOperation(value = "查询平台当日请求数", notes = "查询平台当日请求数")
    @PostMapping("/platformOverview")
    public WinResponseData platformOverview() {
        return WinResponseData.handleSuccess(homeService.getPlatformOverviewData());
    }


    /**
     * 查询平台微服务列表
     * @return
     */
    @ApiOperation(value = "查询平台微服务列表", notes = "查询平台微服务列表")
    @PostMapping("/microServiceState")
    public WinResponseData microServiceState() {
        return WinResponseData.handleSuccess(homeService.getMicroServiceStateData());
    }


}
