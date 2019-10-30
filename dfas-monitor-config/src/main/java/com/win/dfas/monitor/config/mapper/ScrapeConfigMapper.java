package com.win.dfas.monitor.config.mapper;

import com.win.dfas.monitor.common.entity.ScrapeConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  数据层
 * 
 * @author lj
 * @date 2019-10-29
 */
@Repository
public interface ScrapeConfigMapper 
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
     * 删除
     * 
     * @param iD ID
     * @return 结果
     */
	public int deleteScrapeConfigById(String iD);
	
	/**
     * 批量删除
     * 
     * @param iDs 需要删除的数据ID
     * @return 结果
     */
	public int deleteScrapeConfigByIds(String[] iDs);
	
}