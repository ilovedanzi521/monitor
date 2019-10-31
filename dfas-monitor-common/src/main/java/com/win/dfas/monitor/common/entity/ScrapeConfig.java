package com.win.dfas.monitor.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 表 t_scrape_config
 * 
 * @author lj
 * @date 2019-10-29
 */
@Getter
@Setter
@ToString
public class ScrapeConfig
{
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private String id;
	/**  */
	private String jobName;
	/**  */
	private String scheme;
	/**  */
	private Integer scrapeInterval;
	/**  */
	private String metricsPath;
	/**  */
	private String staticConfigsTargets;
	/**  */
	private String staticConfigsLabelsInstance;
	/**  */
	private String consulSdConfigsServer;
	/**  */
	private String consulSdConfigsServername;
	/**  */
	private String consulSdConfigsScheme;
	/**  */
	private String createTime;
	/**  */
	private String updateTime;
}
