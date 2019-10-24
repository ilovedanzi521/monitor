package com.win.dfas.monitor.web.controller;

import com.win.dfas.monitor.common.entity.Issue;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.id.IDUtils;
import com.win.dfas.monitor.common.vo.alert.AlertMsgVO;
import com.win.dfas.monitor.common.vo.alert.AlertVO;
import com.win.dfas.monitor.common.vo.alert.AnnotationVO;
import com.win.dfas.monitor.common.vo.alert.LabelVO;
import com.win.dfas.monitor.engine.service.IDcDevcieService;
import com.win.dfas.monitor.engine.service.IIssueService;
import com.win.dfas.monitor.engine.service.PrometheusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 包名称：com.win.dfas.monitor.web.controller
 * 类名称：AlertController
 * 类描述：预警控制类
 * 创建人：@author lj
 * 创建时间：2019-10-10/13:31
 */
@RestController
@RequestMapping("/alert")
@Api(tags = {""})
public class AlertController extends BaseController {
    @Autowired
    private IIssueService issueService;

    /** 预警数据接收接口 */
    @ApiOperation(value = "预警数据同步", notes = "预警数据同步")
    @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "预警数据", required = true, paramType = "body", dataType = "String")})
    @PostMapping("/sync")
    public String add(@RequestBody String data) {
        AlertMsgVO alertMsgVO = JsonUtil.toObject(data, AlertMsgVO.class);
        Issue issue = getIssueData(alertMsgVO);
        issueService.insertIssue(issue);
        return successData(issue.getId(),"成功");
    }

    private Issue getIssueData(AlertMsgVO alertMsgVO) {
        Issue issue = new Issue();
        AlertVO alertVO = alertMsgVO.getAlerts().get(0);
        LabelVO labels = alertVO.getLabels();
        AnnotationVO annotationVO = alertVO.getAnnotations();
        String warnLevel = labels.getSeverity();
        String description = annotationVO.getDescription();
        String ipAddress = getIP(description);
        String issueDesc = getIssueDesc(ipAddress,description);
        issue.setId(IDUtils.nextId());
        issue.setIpAddress(ipAddress);
        issue.setIssueDesc(issueDesc);
        issue.setWarnLevel(warnLevel);
        return issue;
    }

    private String getIP(String str){
        Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)\\s*(\\d*)");
        Matcher m = pattern.matcher(str);
        while(m.find()) {
            return m.group(1);
        }
        return null;
    }

    private String getIssueDesc(String ipaddress,String data) {
        return data.replace(ipaddress, "").trim() ;
    }

}
