package com.win.dfas.monitor.web.controller;

import com.win.dfas.monitor.common.entity.RuleFileConfig;
import com.win.dfas.monitor.common.entity.ScrapeConfig;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.id.IDUtils;
import com.win.dfas.monitor.engine.service.IRuleFileConfigService;
import com.win.dfas.monitor.engine.service.IScrapeConfigService;
import com.win.dfas.monitor.engine.service.ISysconfigService;
import com.win.dfas.monitor.engine.service.PrometheusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private PrometheusService prometheusService;
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
        ruleFileConfig.setExpr(map.get("expr"));
        ruleFileConfig.setFortime(Integer.parseInt(map.get("fortime")));
        ruleFileConfig.setLabelsSeverity(map.get("labelsSeverity"));
        ruleFileConfig.setAnnotationsSummary(map.get("annotationsSummary"));
        ruleFileConfig.setAnnotationsDescription(map.get("annotationsDescription"));
        ruleFileConfigService.insertRuleFileConfig(ruleFileConfig);
        sysconfigService.syncFile();
        return successData(ruleFileConfig.getId(),"新增预警成功");
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
        RuleFileConfig ruleFileConfig = new RuleFileConfig();
        List<RuleFileConfig> dcDevices = ruleFileConfigService.selectRuleFileConfigList(ruleFileConfig);
        Map<String,Object> result = new HashMap<>();
        result.put("total",dcDevices.size());
        result.put("list",dcDevices);
       return successData(result,"操作成功");
    }
}
