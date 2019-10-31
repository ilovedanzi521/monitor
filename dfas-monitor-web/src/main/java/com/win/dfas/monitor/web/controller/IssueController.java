package com.win.dfas.monitor.web.controller;

import com.win.dfas.monitor.common.entity.Issue;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.engine.service.IIssueService;
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
 * 类名称：IssueController
 * 类描述：问题控制类
 * 创建人：@author lj
 * 创建时间：2019-10-10/13:31
 */
@RestController
@RequestMapping("/issue")
@Api(tags = {"机器信息交互接口"})
public class IssueController extends BaseController {

    @Autowired
    private IIssueService issueService;

    /** 查看机器 */
    /*@ApiOperation(value = "查看问题", notes = "查看问题")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "查看问题信息", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/view")
    public String view(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        Issue issue = issueService.selectIssueById(map.get("id"));
        return successData(issue,"查看问题成功");
    }*/

    /** 删除问题 */
    @ApiOperation(value = "删除问题", notes = "删除问题")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "删除问题", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/delete")
    public String delete(@RequestBody String data) {
        Map<String,String> map = JsonUtil.toObject(data, Map.class);
        String id = map.get("id") ;
        issueService.deleteIssueByIds(id);
        return successData(id,"删除问题成功");
    }
    /** 修改机器 */
    @ApiOperation(value = "批量删除问题", notes = "批量删除问题")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "问题信息", required = true, paramType = "body", dataType = "String")})
    @DeleteMapping("/batchDelete/{ids}")
    public String batchDelete(@PathVariable("ids") String ids) {
        issueService.deleteIssueByIds(ids);
        return successData(ids,"删除问题成功");
    }

    /** 机器列表 */
    @ApiOperation(value = "问题列表", notes = "问题列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "问题列表", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/issuePage")
    public String issuePage(@RequestBody String data) {
        Issue issue = new Issue();
        List<Issue> issues = issueService.selectIssueList(issue);
        Map<String,Object> result = new HashMap<>();
        result.put("total",issues.size());
        result.put("list",issues);
       return successData(result,"操作成功");
    }
}
