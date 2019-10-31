package com.win.dfas.monitor.engine.service.impl;

import com.win.dfas.monitor.common.constant.MonitorConstants;
import com.win.dfas.monitor.common.entity.RuleFileConfig;
import com.win.dfas.monitor.common.entity.ScrapeConfig;
import com.win.dfas.monitor.common.util.Convert;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.util.StringUtils;
import com.win.dfas.monitor.config.mapper.RuleFileConfigMapper;
import com.win.dfas.monitor.engine.service.IRuleFileConfigService;
import com.win.dfas.monitor.engine.service.IScrapeConfigService;
import com.win.dfas.monitor.engine.service.ISysconfigService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  服务层实现
 * 
 * @author lj
 * @date 2019-10-29
 */
@Service
public class SysconfigServiceImpl implements ISysconfigService
{
	@Value("${alertmanager.server.url}")
	private String alertmanagerServerUrl;

	@Value("${prometheus.server.url}")
	private String prometheusServerUrl;

	@Value("${prometheus.config.dir}")
	private String prometheusConfigDir;


	@Autowired
	private IScrapeConfigService scrapeConfigService;
	@Autowired
	private IRuleFileConfigService ruleFileConfigService;

	@Override
	public void syncFile() {
		/*Map<String,Object> resultMap = generateSystemConfiguration();
		sync2prometheus(resultMap);
		clearLocalFile(resultMap);
		String url = prometheusServerUrl + "/-/reload";
		RestfulTools.post(url, String.class);*/
	}

	@Override
	public Map<String,Object> generateSystemConfiguration() {
		Map<String,Object> resultMap = new HashMap<>();
		ScrapeConfig scrapeConfig = new ScrapeConfig();
		List<ScrapeConfig> scrapeConfigs = scrapeConfigService.selectScrapeConfigList(scrapeConfig);
		RuleFileConfig ruleFileConfig = new RuleFileConfig();
		List<RuleFileConfig> ruleFileConfigs = ruleFileConfigService.selectRuleFileConfigList(ruleFileConfig);
		List<String> files = generateAlertRuleConfiguration(ruleFileConfigs,resultMap);
		generateSystemConfiguration(alertmanagerServerUrl,files,scrapeConfigs,resultMap);
		return resultMap;
	}

	@Override
	public void sync2prometheus(Map<String, Object> resultMap) {
       //System.out.println(resultMap);
		BufferedInputStream bis = null;
		BufferedReader br = null;
		Process process = null;
		try {
			String cmd = getCmd(resultMap);
			Runtime run = Runtime.getRuntime();
			process = run.exec(new String[] { "/bin/sh", "-c", cmd});
			bis = new BufferedInputStream(process.getInputStream());
			br = new BufferedReader(new InputStreamReader(bis));
			StringBuilder sb = new StringBuilder();
			String lineStr = null;
			while ((lineStr = br.readLine()) != null) {
				sb.append(lineStr);
			}
			int resultCode = process.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		process.destroy();

	}

	@Override
	public void clearLocalFile(Map<String, Object> resultMap) {
		//System.out.println(resultMap);
		clearLocalTempFile(resultMap);
	}

	private static List<String> generateAlertRuleConfiguration(List<RuleFileConfig> ruleFileConfigs,Map<String,Object> resultMap) {
		List<String> files = new ArrayList<>();
		List<String> filePaths = new ArrayList<>();
		for(RuleFileConfig ruleFileConfig : ruleFileConfigs){
			String alertRuleFileName = getRandomFileName("yml");
			files.add(alertRuleFileName);
			StringBuffer alertRuleConfig = new StringBuffer();
			alertRuleConfig.append("groups:");
			alertRuleConfig.append("\n");
			alertRuleConfig.append("- name: "+ ruleFileConfig.getGroupsName());
			alertRuleConfig.append("\n");
			alertRuleConfig.append("  rules:");
			alertRuleConfig.append("\n");
			alertRuleConfig.append("  - alert: " + ruleFileConfig.getName());
			alertRuleConfig.append("\n");
			alertRuleConfig.append("    expr: " + ruleFileConfig.getExpr());
			alertRuleConfig.append("\n");
			alertRuleConfig.append("    for: " + ruleFileConfig.getFortime() + "m");
			alertRuleConfig.append("\n");
			alertRuleConfig.append("    labels:");
			alertRuleConfig.append("\n");
			alertRuleConfig.append("      severity: " + ruleFileConfig.getLabelsSeverity());
			alertRuleConfig.append("\n");
			alertRuleConfig.append("    annotations:");
			alertRuleConfig.append("\n");
			alertRuleConfig.append("      summary: \""+ruleFileConfig.getAnnotationsSummary()+"\"");
			alertRuleConfig.append("\n");
			alertRuleConfig.append("      description: \""+ruleFileConfig.getAnnotationsDescription()+"\"");
			alertRuleConfig.append("\n");

			String filePath = writeStringToFile(alertRuleFileName,alertRuleConfig.toString());
			filePaths.add(filePath);
			//System.out.println(alertRuleFileName);
			//System.out.println(alertRuleConfig.toString());
		}
		resultMap.put(MonitorConstants.RESULTMAP_ALERTRULE,filePaths);
		return files;
	}

	private static String writeStringToFile(String fileName,String data){
		String tempDirectoryPath = FileUtils.getTempDirectoryPath();
		String filePath = tempDirectoryPath + fileName ;
		try {
			FileUtils.writeStringToFile(new File(filePath),data, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}


	private static void generateSystemConfiguration(String alertManagerServerUrl, List<String> files, List<ScrapeConfig> scrapeConfigs,Map<String,Object> resultMap) {
		StringBuffer sysConfig = new StringBuffer();
		sysConfig.append(getGlobalConfig());
		sysConfig.append(getAlertmanagerConfig(alertManagerServerUrl));
		sysConfig.append(getAlertRuleFilesConfig(files));
		sysConfig.append(getScrapeConfig(scrapeConfigs));
		String fileName = getRandomFileName("yml");
		String filePath = writeStringToFile(fileName,sysConfig.toString());
		resultMap.put(MonitorConstants.RESULTMAP_PROMETHEUS,filePath);
	}

	private static String getGlobalConfig() {
		StringBuffer config = new StringBuffer();
		config.append("# my global config");
		config.append("\n");
		config.append("global:");
		config.append("\n");
		config.append("  scrape_interval:     15s # Set the scrape interval to every 15 seconds. Default is every 1 minute.");
		config.append("\n");
		config.append("  evaluation_interval: 15s # Evaluate rules every 15 seconds. The default is every 1 minute.");
		config.append("\n");
		return config.toString();
	}

	private static String getAlertmanagerConfig(String alertManagerServerUrl) {
		StringBuffer config = new StringBuffer();
		config.append("alerting:");
		config.append("\n");
		config.append("  alertmanagers:");
		config.append("\n");
		config.append("  - static_configs:");
		config.append("\n");
		config.append("    - targets: ['"+alertManagerServerUrl+"']");
		config.append("\n");
		return config.toString();
	}

	private static String getAlertRuleFilesConfig(List<String> files) {
		StringBuffer config = new StringBuffer();
		config.append("rule_files:");
		config.append("\n");
		for (String file : files){
			config.append("  - \""+file+"\"");
			config.append("\n");
		}
		return config.toString();
	}

	private static String getScrapeConfig(List<ScrapeConfig> scrapeConfigs) {
		StringBuffer config = new StringBuffer();
		config.append("# A scrape configuration containing exactly one endpoint to scrape:");
		config.append("\n");
		config.append("# Here it's Prometheus itself.");
		config.append("\n");
		config.append("scrape_configs:");
		config.append("\n");
		for (ScrapeConfig scrapeConfig : scrapeConfigs){
			config.append("  - job_name: '"+scrapeConfig.getJobName()+"'");
			config.append("\n");
			if(null != scrapeConfig.getScrapeInterval() && scrapeConfig.getScrapeInterval() != 0){
				config.append("    scrape_interval: "+scrapeConfig.getScrapeInterval()+"s");
				config.append("\n");
			}
			if(StringUtils.isNotEmpty(scrapeConfig.getStaticConfigsTargets())){
				config.append("    static_configs:");
				config.append("\n");
				config.append("    - targets: ['"+scrapeConfig.getStaticConfigsTargets()+"']");
				config.append("\n");
				if(StringUtils.isNotEmpty(scrapeConfig.getStaticConfigsLabelsInstance())){
					config.append("      labels:");
					config.append("\n");
					config.append("        instance: "+scrapeConfig.getStaticConfigsLabelsInstance());
					config.append("\n");
				}
			}
			if(StringUtils.isNotEmpty(scrapeConfig.getMetricsPath())){
				config.append("    metrics_path: '"+scrapeConfig.getMetricsPath()+"'");
				config.append("\n");
			}
			if(StringUtils.isNotEmpty(scrapeConfig.getScheme())){
				config.append("    scheme: "+scrapeConfig.getScheme());
				config.append("\n");
			}
			if(StringUtils.isNotEmpty(scrapeConfig.getConsulSdConfigsServer())){
				config.append("    consul_sd_configs:");
				config.append("\n");
				config.append("      - server: '"+scrapeConfig.getConsulSdConfigsServer()+"'");
				config.append("\n");
				if(StringUtils.isNotEmpty(scrapeConfig.getConsulSdConfigsScheme())){
					config.append("        scheme: " + scrapeConfig.getConsulSdConfigsScheme());
					config.append("\n");
				}else{
					config.append("        scheme: http");
					config.append("\n");
				}
			}
			config.append("\n");
		}
		return config.toString();
	}

	private static String getRandomFileName(String fileType) {
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		return rannum + str + "." + fileType;
	}

	private String getCmd(Map<String, Object> resultMap){
		StringBuffer cmd = new StringBuffer();
		URI uri = URI.create(prometheusServerUrl);
		String host = uri.getHost();
		String prometheusFilepath = (String)resultMap.get(MonitorConstants.RESULTMAP_PROMETHEUS);
		cmd.append("scp "+prometheusFilepath+" "+host+":"+prometheusConfigDir+"/prometheus.yml");
		cmd.append("\n");
		List<String> alertRuleFiles = (ArrayList<String>)resultMap.get(MonitorConstants.RESULTMAP_ALERTRULE);
		for (String alertRuleFile : alertRuleFiles){
			cmd.append("scp "+alertRuleFile+" "+host+":"+prometheusConfigDir+"/" + FilenameUtils.getName(alertRuleFile));
			cmd.append("\n");
		}
		return cmd.toString();
	}

	private void clearLocalTempFile(Map<String, Object> resultMap){
		try {
			String prometheusFilepath = (String)resultMap.get(MonitorConstants.RESULTMAP_PROMETHEUS);
			FileUtils.forceDelete(new File(prometheusFilepath));
			List<String> alertRuleFiles = (ArrayList<String>)resultMap.get(MonitorConstants.RESULTMAP_ALERTRULE);
			for (String alertRuleFile : alertRuleFiles){
				FileUtils.forceDelete(new File(alertRuleFile));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
