package com.win.dfas.monitor.web.issue;


import com.win.dfas.monitor.common.entity.Issue;
import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.id.IDUtils;
import com.win.dfas.monitor.engine.MonitorEngineInitializer;
import com.win.dfas.monitor.engine.service.IIssueService;
import com.win.dfas.monitor.exporter.microservice.EnableMonitorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@EnableMonitorConfig
@EnableScheduling
@MapperScan("com.win.dfas.monitor.config.mapper")
@Import(MonitorEngineInitializer.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IssueTest {

    @Autowired
    private IIssueService issueService;

    @Test
    public void test_insertIssue()throws Exception {
        Issue issue = new Issue();
        issue.setId(IDUtils.nextId());
        issue.setIpAddress("192.168.0.81");
        issue.setIssueDesc("CPU使用率超过90%");
        issue.setCreateTime(DateUtils.getCurrentDateTime());
        issueService.insertIssue(issue);
    }




}
