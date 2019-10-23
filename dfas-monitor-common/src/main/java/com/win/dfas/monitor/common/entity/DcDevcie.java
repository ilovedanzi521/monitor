package com.win.dfas.monitor.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 机器表 dc_devcie
 * 
 * @author lj
 * @date 2019-10-10
 */
@Getter
@Setter
@ToString
public class DcDevcie
{
	/** 主键ID */
	private String id;
	/** 机器名称 */
	private String name;
	/** 机器别名 */
	private String alias;
	/** ip地址：IPV4 */
	private String ipAddress;
	/** 用户名：SSH/SCP LOGIN USER */
	private String userName;
	/** 端口号：SSH/SCP PORT */
	private Integer port;
	/** 操作系统类型 */
	private String osType;
	/** 状态：0-未连接;1-连接成功;2-连接失败 */
	private Integer status;
	/** 描述 */
	private String description;
	/** 创建时间 */
	private String createTime;
	/** 更新时间 */
	private String updateTime;

	/** cpu使用率 */
	private String cpu;

	/** cpu核数 */
	private String cpuCore;

	/** 内存使用率 */
	private String memory;

	/** 磁盘使用率 */
	private String disk;

	/** cpu核数 */
	private Integer cpuNum;
	/** 磁盘大小 */
	private String diskSize;
	/** 内存大小 */
	private String memorySize;
	/** 负载 */
	private  String balance;

	/** 资源明细页显示CPU信息 */
	private  String cpuInfo;
	/** 资源明细页显示磁盘信息 */
	private  String diskInfo;
	/** 资源明细页显示内存信息 */
	private  String memoryInfo;
	/** 资源明细页显示负载信息 */
	private  String balanceInfo;

}
