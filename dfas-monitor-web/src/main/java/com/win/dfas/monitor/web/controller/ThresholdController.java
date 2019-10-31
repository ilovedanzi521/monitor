package com.win.dfas.monitor.web.controller;

import com.win.dfas.monitor.common.entity.Threshold;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.id.IDUtils;
import com.win.dfas.monitor.engine.service.IThresholdService;
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
 * 类名称：ThresholdController
 * 类描述：阈值控制类
 * 创建人：@author lj
 * 创建时间：2019-10-10/13:31
 */
@RestController
@RequestMapping("/threshold")
@Api(tags = {"机器信息交互接口"})
public class ThresholdController extends BaseController {

    @Autowired
    private IThresholdService thresholdService;

    /** 新增阈值 */
    @ApiOperation(value = "新增阈值", notes = "新增阈值")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "阈值信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/add")
    public String add(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        Threshold threshold = new Threshold();
        threshold.setId(IDUtils.nextId());
        threshold.setIndicatorBody(map.get("indicatorBody"));
        threshold.setIndicatorName(map.get("indicatorName"));
        threshold.setIndicatorSymbol(map.get("indicatorSymbol"));
        threshold.setThreshold(Integer.parseInt(map.get("threshold")));
        thresholdService.insertThreshold(threshold);
        return successData(threshold.getId(),"新增阈值成功");
    }

    /** 修改阈值 */
    @ApiOperation(value = "修改阈值", notes = "修改阈值")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "阈值信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/modify")
    public String modify(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        Threshold threshold = new Threshold();
        threshold.setId(map.get("id"));
        threshold.setIndicatorBody(map.get("indicatorBody"));
        threshold.setIndicatorName(map.get("indicatorName"));
        threshold.setIndicatorSymbol(map.get("indicatorSymbol"));
        threshold.setThreshold(Integer.parseInt(map.get("threshold")));
        thresholdService.updateThreshold(threshold);
        return successData(threshold.getId(),"修改阈值成功");
    }

    /** 删除阈值 */
    @ApiOperation(value = "删除阈值", notes = "删除阈值")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "阈值信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/delete")
    public String delete(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        String id = map.get("id") ;
        thresholdService.deleteThresholdByIds(id);
        return successData(id,"删除阈值成功");
    }
    /** 批量删除阈值 */
    @ApiOperation(value = "批量删除阈值", notes = "批量删除阈值")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "阈值信息", required = true, paramType = "body", dataType = "String")})
    @DeleteMapping("/batchDelete/{ids}")
    public String batchDelete(@PathVariable("ids") String ids) {
        thresholdService.deleteThresholdByIds(ids);
        return successData(ids,"删除阈值成功");
    }

    /** 阈值列表 */
    @ApiOperation(value = "阈值列表", notes = "阈值列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "阈值列表", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/thresholdPage")
    public String machinePage(@RequestBody String data) {
        Threshold threshold = new Threshold();
        List<Threshold> thresholds  = thresholdService.selectThresholdList(threshold);
        Map<String,Object> result = new HashMap<>();
        result.put("total",thresholds.size());
        result.put("list",thresholds);
       return successData(result,"操作成功");
    }
}
