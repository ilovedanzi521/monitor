package com.win.dfas.monitor.engine.service;

import com.win.dfas.monitor.common.entity.ScrapeConfig;

import java.util.List;
import java.util.Map;

/**
 *  系统配置服务
 * 
 * @author lj
 * @date 2019-10-29
 */
public interface ISysconfigService
{
	public void syncFile();
	/**
     * 生成系统配置
     *
     * @return
     */
	public Map<String,Object> generateSystemConfiguration();

	/**
	 * 同步文件到prometheus
	 *
	 * @return 信
	 */
	public void sync2prometheus(Map<String,Object> resultMap);

	/**
	 * 清空本地临时文件
	 *
	 * @return 信
	 *
	 */

	public void clearLocalFile(Map<String,Object> resultMap);


}
