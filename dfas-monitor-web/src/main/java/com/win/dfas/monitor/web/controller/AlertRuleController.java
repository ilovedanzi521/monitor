package com.win.dfas.monitor.web.controller;

import com.win.dfas.monitor.common.entity.RuleFileConfig;
import com.win.dfas.monitor.common.entity.ScrapeConfig;
import com.win.dfas.monitor.common.entity.Threshold;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.StringUtils;
import com.win.dfas.monitor.common.util.id.IDUtils;
import com.win.dfas.monitor.engine.service.*;
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
 * 类名称：AlertRuleController
 * 类描述：预警配置控制类
 * 创建人：@author lj
 * 创建时间：2019-10-10/13:31
 */
@RestController
@RequestMapping("/alertRule")
@Api(tags = {"预警配置交互接口"})
public class AlertRuleController extends BaseController {

    @Autowired
    private IRuleFileConfigService ruleFileConfigService;
    @Autowired
    private IScrapeConfigService scrapeConfigService;

    @Autowired
    private IThresholdService thresholdService;

    @Autowired
    ISysconfigService sysconfigService;

    /** 新增预警 */
    @ApiOperation(value = "新增预警", notes = "新增预警")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "预警信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/add")
    public String add(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        RuleFileConfig ruleFileConfig = new RuleFileConfig();
        ruleFileConfig.setId(IDUtils.nextId());
        ruleFileConfig.setGroupsName(map.get("groupsName"));
        ruleFileConfig.setName(map.get("name"));
        //ruleFileConfig.setExpr(map.get("expr"));
        /*
        *  alertRuleType: this.alertRule.alertRuleType,
        ipAddress: this.alertRule.ipAddress,
        * */
        String alertRuleType = map.get("alertRuleType");
        String instanceLabel = map.get("ipAddress");
        Threshold threshold = thresholdService.selectThresholdByIndicatorBody(alertRuleType);
        String expr = getExpr(alertRuleType,instanceLabel,threshold);
        ruleFileConfig.setExpr(expr);
        ruleFileConfig.setFortime(Integer.parseInt(map.get("fortime")));
        ruleFileConfig.setLabelsSeverity(alertRuleType + "_" +map.get("labelsSeverity"));
        ruleFileConfig.setAnnotationsSummary(map.get("annotationsSummary"));
        ruleFileConfig.setAnnotationsDescription(map.get("annotationsDescription"));
        ruleFileConfigService.insertRuleFileConfig(ruleFileConfig);
        sysconfigService.syncFile();
        return successData(ruleFileConfig.getId(),"新增预警成功");
    }

    private String getExpr(String alertRuleType, String instanceLabel, Threshold threshold) {
       StringBuffer expr = new StringBuffer();
        // (100 - (avg(irate(node_cpu_seconds_total{instance='expoter_192.168.0.81',mode='idle'}[5m])) * 100) ) > 2
        if("cpu".equals(alertRuleType)){
            expr.append("(100 - (avg(irate(node_cpu_seconds_total{instance='");
            expr.append(instanceLabel);
            expr.append("',mode='idle'}[5m])) * 100) ) ");
            expr.append(threshold.getIndicatorSymbol());
            expr.append(" ");
            expr.append(threshold.getThreshold());
       // ((1 - (node_memory_MemAvailable_bytes{instance='expoter_192.168.0.81'} / (node_memory_MemTotal_bytes{instance='expoter_192.168.0.81'})))* 100) > 10
       }else if("memory".equals(alertRuleType)){
            expr.append("((1 - (node_memory_MemAvailable_bytes{instance='");
            expr.append(instanceLabel);
            expr.append("'} / (node_memory_MemTotal_bytes{instance='");
            expr.append(instanceLabel);
            expr.append("'})))* 100) ");
            expr.append(threshold.getIndicatorSymbol());
            expr.append(" ");
            expr.append(threshold.getThreshold());
        }else if("disk".equals(alertRuleType)){

        }else if("microServiceJvm".equals(alertRuleType)){

        }
        return expr.toString();
    }

    /** 修改预警 */
    @ApiOperation(value = "修改预警", notes = "修改预警")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "预警信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/modify")
    public String modify(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        RuleFileConfig ruleFileConfig = new RuleFileConfig();
        ruleFileConfig.setId(map.get("id"));
        ruleFileConfig.setGroupsName(map.get("groupsName"));
        ruleFileConfig.setName(map.get("name"));
        ruleFileConfig.setExpr(map.get("expr"));
        ruleFileConfig.setFortime(Integer.parseInt(map.get("fortime")));
        ruleFileConfig.setLabelsSeverity(map.get("labelsSeverity"));
        ruleFileConfig.setAnnotationsSummary(map.get("annotationsSummary"));
        ruleFileConfig.setAnnotationsDescription(map.get("annotationsDescription"));
        ruleFileConfigService.updateRuleFileConfig(ruleFileConfig);
        sysconfigService.syncFile();
        return successData(ruleFileConfig.getId(),"修改预警成功");
    }

    /** 修改预警 */
    @ApiOperation(value = "删除预警", notes = "删除预警")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "预警信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/delete")
    public String delete(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        String id = map.get("id") ;
        ruleFileConfigService.deleteRuleFileConfigByIds(id);
        sysconfigService.syncFile();
        return successData(id,"删除预警成功");
    }
    /** 修改预警 */
    @ApiOperation(value = "批量删除预警", notes = "批量删除预警")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "预警信息", required = true, paramType = "body", dataType = "String")})
    @DeleteMapping("/batchDelete/{ids}")
    public String batchDelete(@PathVariable("ids") String ids) {
        ruleFileConfigService.deleteRuleFileConfigByIds(ids);
        sysconfigService.syncFile();
        return successData(ids,"删除预警成功");
    }

    /** 预警列表 */
    @ApiOperation(value = "预警列表", notes = "预警列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "预警列表", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/alertRulePage")
    public String alertRulePage(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        RuleFileConfig ruleFileConfig = new RuleFileConfig();
        if(StringUtils.isNotEmpty(map.get("name"))){
            ruleFileConfig.setName(map.get("name"));
        }
        List<RuleFileConfig> dcDevices = ruleFileConfigService.selectRuleFileConfigList(ruleFileConfig);
        Map<String,Object> result = new HashMap<>();
        result.put("total",dcDevices.size());
        result.put("list",dcDevices);
       return successData(result,"操作成功");
    }

    /** ip信息列表 */
    @ApiOperation(value = "ip信息列表", notes = "ip信息列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "预警列表", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/getIpAddressSelect")
    public String getIpAddressSelect(@RequestBody String data) {
        ScrapeConfig scrapeConfig = new ScrapeConfig();
        List<ScrapeConfig> scrapeConfigs = scrapeConfigService.selectScrapeConfigList(scrapeConfig);
        List<Map<String,String>> result = new ArrayList<>();
        for(ScrapeConfig sc : scrapeConfigs ){
            String staticConfigsLabelsInstance = sc.getStaticConfigsLabelsInstance();
            if(StringUtils.isNotEmpty(staticConfigsLabelsInstance)){
                 String ip = staticConfigsLabelsInstance.replace("expoter_" , "");
                 Map<String,String> ipMap = new HashMap<>();
                ipMap.put("code",ip);
                ipMap.put("value",staticConfigsLabelsInstance);
                result.add(ipMap);
            }
        }
        return successData(result,"操作成功");
    }

    /** 预警类型列表 */
    @ApiOperation(value = "预警类型列表", notes = "预警类型列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "预警类型列表", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/getAlertTypeSelect")
    public String getAlertTypeSelect(@RequestBody String data) {
        Threshold threshold = new Threshold();
        List<Threshold> thresholds  = thresholdService.selectThresholdList(threshold);
        List<Map<String,String>> result = new ArrayList<>();
        for(Threshold ts : thresholds ){
            String code = ts.getIndicatorBody();
            String value = ts.getIndicatorName();
            Map<String,String> map = new HashMap<>();
            map.put("alertRuleType",code);
            map.put("value",value);
            result.add(map);
        }
        return successData(result,"操作成功");
    }

}
