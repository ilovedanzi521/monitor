package com.win.dfas.monitor.engine.service;

import com.win.dfas.monitor.common.entity.ScrapeConfig;

import java.util.List;

/**
 *  服务层
 * 
 * @author lj
 * @date 2019-10-29
 */
public interface IScrapeConfigService 
{
	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
	public ScrapeConfig selectScrapeConfigById(String id);
	
	/**
     * 查询列表
     * 
     * @param scrapeConfig 信息
     * @return 集合
     */
	public List<ScrapeConfig> selectScrapeConfigList(ScrapeConfig scrapeConfig);
	
	/**
     * 新增
     * 
     * @param scrapeConfig 信息
     * @return 结果
     */
	public int insertScrapeConfig(ScrapeConfig scrapeConfig);
	
	/**
     * 修改
     * 
     * @param scrapeConfig 信息
     * @return 结果
     */
	public int updateScrapeConfig(ScrapeConfig scrapeConfig);
		
	/**
     * 删除信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteScrapeConfigByIds(String ids);
	
}
