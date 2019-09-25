package com.win.dfas.monitor.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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


    /** 监控平台主控面板操作对外服务*/
    @ApiOperation(value = "监控平台主控面板操作对外服务", notes = "监控平台主控面板操作对外服务")
    @GetMapping("/httpRequestTotal")
    public String httpRequestTotal() {
        String url="http://192.168.0.55:9090/api/v1/query?query=http_requests_total_20190925";
        return successData("");
    }

}
