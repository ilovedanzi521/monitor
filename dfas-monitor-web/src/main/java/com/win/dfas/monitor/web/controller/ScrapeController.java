package com.win.dfas.monitor.web.controller;

import com.win.dfas.monitor.common.entity.DcDevcie;
import com.win.dfas.monitor.common.entity.ScrapeConfig;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.StringUtils;
import com.win.dfas.monitor.common.util.id.IDUtils;
import com.win.dfas.monitor.common.vo.MachineStatusVO;
import com.win.dfas.monitor.common.vo.MachineVO;
import com.win.dfas.monitor.engine.service.IDcDevcieService;
import com.win.dfas.monitor.engine.service.IScrapeConfigService;
import com.win.dfas.monitor.engine.service.PrometheusService;
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
 * 类名称：ScrapeController
 * 类描述：刮取配置控制类
 * 创建人：@author lj
 * 创建时间：2019-10-10/13:31
 */
@RestController
@RequestMapping("/scrape")
@Api(tags = {"刮取配置交互接口"})
public class ScrapeController extends BaseController {

    @Autowired
    private IScrapeConfigService scrapeConfigService;
    @Autowired
    private PrometheusService prometheusService;

    /** 新增刮取 */
    @ApiOperation(value = "新增刮取", notes = "新增刮取")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "刮取信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/add")
    public String add(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        ScrapeConfig scrapeConfig = new ScrapeConfig();
        scrapeConfig.setId(IDUtils.nextId());
        scrapeConfig.setJobName(map.get("jobName"));
        scrapeConfig.setScheme(map.get("scheme"));
        scrapeConfig.setScrapeInterval(Integer.parseInt(map.get("scrapeInterval")));
        scrapeConfig.setMetricsPath(map.get("metricsPath"));
        scrapeConfig.setStaticConfigsTargets(map.get("staticConfigsTargets"));
        scrapeConfig.setStaticConfigsLabelsInstance(map.get("staticConfigsLabelsInstance"));
        scrapeConfig.setConsulSdConfigsServer(map.get("consulSdConfigsServer"));
        scrapeConfig.setConsulSdConfigsServername(map.get("consulSdConfigsServername"));
        scrapeConfig.setConsulSdConfigsScheme(map.get("consulSdConfigsScheme"));
        scrapeConfigService.insertScrapeConfig(scrapeConfig);
        return successData(scrapeConfig.getId(),"新增刮取成功");
    }

    /** 修改刮取 */
    @ApiOperation(value = "修改刮取", notes = "修改刮取")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "刮取信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/modify")
    public String modify(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        ScrapeConfig scrapeConfig = new ScrapeConfig();
        scrapeConfig.setId(map.get("id"));
        scrapeConfig.setJobName(map.get("jobName"));
        scrapeConfig.setScheme(map.get("scheme"));
        scrapeConfig.setScrapeInterval(Integer.parseInt(map.get("scrapeInterval")));
        scrapeConfig.setMetricsPath(map.get("metricsPath"));
        scrapeConfig.setStaticConfigsTargets(map.get("staticConfigsTargets"));
        scrapeConfig.setStaticConfigsLabelsInstance(map.get("staticConfigsLabelsInstance"));
        scrapeConfig.setConsulSdConfigsServer(map.get("consulSdConfigsServer"));
        scrapeConfig.setConsulSdConfigsServername(map.get("consulSdConfigsServername"));
        scrapeConfig.setConsulSdConfigsScheme(map.get("consulSdConfigsScheme"));
        scrapeConfigService.updateScrapeConfig(scrapeConfig);
        return successData(scrapeConfig.getId(),"修改刮取成功");
    }

    /** 修改刮取 */
    @ApiOperation(value = "删除刮取", notes = "删除刮取")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "刮取信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/delete")
    public String delete(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        String id = map.get("id") ;
        scrapeConfigService.deleteScrapeConfigByIds(id);
        return successData(id,"删除刮取成功");
    }
    /** 修改刮取 */
    @ApiOperation(value = "批量删除刮取", notes = "批量删除刮取")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "刮取信息", required = true, paramType = "body", dataType = "String")})
    @DeleteMapping("/batchDelete/{ids}")
    public String batchDelete(@PathVariable("ids") String ids) {
        scrapeConfigService.deleteScrapeConfigByIds(ids);
        return successData(ids,"删除刮取成功");
    }

    /** 刮取列表 */
    @ApiOperation(value = "刮取列表", notes = "刮取列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "刮取列表", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/scrapePage")
    public String scrapePage(@RequestBody String data) {
        ScrapeConfig scrapeConfig = new ScrapeConfig();
        List<ScrapeConfig> dcDevices = scrapeConfigService.selectScrapeConfigList(scrapeConfig);
        Map<String,Object> result = new HashMap<>();
        result.put("total",dcDevices.size());
        result.put("list",dcDevices);
       return successData(result,"操作成功");
    }
}
