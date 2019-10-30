package com.win.dfas.monitor.engine.service.impl;

import java.util.List;

import com.win.dfas.monitor.common.entity.ScrapeConfig;
import com.win.dfas.monitor.common.util.Convert;
import com.win.dfas.monitor.config.mapper.ScrapeConfigMapper;
import com.win.dfas.monitor.engine.service.IScrapeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  服务层实现
 * 
 * @author lj
 * @date 2019-10-29
 */
@Service
public class ScrapeConfigServiceImpl implements IScrapeConfigService
{
	@Autowired
	private ScrapeConfigMapper scrapeConfigMapper;

	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
    @Override
	public ScrapeConfig selectScrapeConfigById(String id)
	{
	    return scrapeConfigMapper.selectScrapeConfigById(id);
	}
	
	/**
     * 查询列表
     * 
     * @param scrapeConfig 信息
     * @return 集合
     */
	@Override
	public List<ScrapeConfig> selectScrapeConfigList(ScrapeConfig scrapeConfig)
	{
	    return scrapeConfigMapper.selectScrapeConfigList(scrapeConfig);
	}
	
    /**
     * 新增
     * 
     * @param scrapeConfig 信息
     * @return 结果
     */
	@Override
	public int insertScrapeConfig(ScrapeConfig scrapeConfig)
	{
	    return scrapeConfigMapper.insertScrapeConfig(scrapeConfig);
	}
	
	/**
     * 修改
     * 
     * @param scrapeConfig 信息
     * @return 结果
     */
	@Override
	public int updateScrapeConfig(ScrapeConfig scrapeConfig)
	{
	    return scrapeConfigMapper.updateScrapeConfig(scrapeConfig);
	}

	/**
     * 删除对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteScrapeConfigByIds(String ids)
	{
		return scrapeConfigMapper.deleteScrapeConfigByIds(Convert.toStrArray(ids));
	}
	
}
