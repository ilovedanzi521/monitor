package com.win.dfas.monitor.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 表 t_rule_file_config
 * 
 * @author lj
 * @date 2019-10-29
 */
@Getter
@Setter
@ToString
public class RuleFileConfig
{
	/**  */
	private String id;
	/**  */
	private String groupsName;
	/**  */
	private String name;
	/**  */
	private String expr;
	/**  */
	private Integer fortime;
	/**  */
	private String labelsSeverity;
	/**  */
	private String annotationsSummary;
	/**  */
	private String annotationsDescription;
	/**  */
	private String createTime;
	/**  */
	private String updateTime;

}
