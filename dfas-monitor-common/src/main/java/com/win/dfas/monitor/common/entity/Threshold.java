package com.win.dfas.monitor.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 阈值表 t_threshold
 *
 * @author lj
 * @date 2019-10-10
 */
@Getter
@Setter
@ToString
public class Threshold
{
	/**  */
	private String id;
	/**  */
	private String indicatorBody;
	/**  */
	private String indicatorName;
	/**  */
	private String indicatorSymbol;
	/**  */
	private Integer threshold;
	/**  */
	private String createTime;
	/**  */
	private String updateTime;
}
