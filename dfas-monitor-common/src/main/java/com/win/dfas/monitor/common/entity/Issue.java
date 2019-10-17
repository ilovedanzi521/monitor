package com.win.dfas.monitor.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 问题表 t_issue
 *
 * @author lj
 * @date 2019-10-10
 */
@Getter
@Setter
@ToString
public class Issue
{
	/**  */
	private String id;
	/**  */
	private String ipAddress;
	/**  */
	private String issueDesc;
	/**  */
	private String warnLevel;
	/**  */
	private String createTime;
	/**  */
	private String updateTime;
}
