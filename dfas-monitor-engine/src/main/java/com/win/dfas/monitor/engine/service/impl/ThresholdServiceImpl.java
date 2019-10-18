package com.win.dfas.monitor.engine.service.impl;

import java.util.List;
import com.win.dfas.monitor.common.entity.Threshold;
import com.win.dfas.monitor.common.util.Convert;
import com.win.dfas.monitor.config.mapper.ThresholdMapper;
import com.win.dfas.monitor.engine.service.IThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  服务层实现
 * 
 * @author yss
 * @date 2019-10-18
 */
@Service
public class ThresholdServiceImpl implements IThresholdService
{
	@Autowired
	private ThresholdMapper thresholdMapper;

	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
    @Override
	public Threshold selectThresholdById(String id)
	{
	    return thresholdMapper.selectThresholdById(id);
	}
	
	/**
     * 查询列表
     * 
     * @param threshold 信息
     * @return 集合
     */
	@Override
	public List<Threshold> selectThresholdList(Threshold threshold)
	{
	    return thresholdMapper.selectThresholdList(threshold);
	}
	
    /**
     * 新增
     * 
     * @param threshold 信息
     * @return 结果
     */
	@Override
	public int insertThreshold(Threshold threshold)
	{
	    return thresholdMapper.insertThreshold(threshold);
	}
	
	/**
     * 修改
     * 
     * @param threshold 信息
     * @return 结果
     */
	@Override
	public int updateThreshold(Threshold threshold)
	{
	    return thresholdMapper.updateThreshold(threshold);
	}

	/**
     * 删除对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteThresholdByIds(String ids)
	{
		return thresholdMapper.deleteThresholdByIds(Convert.toStrArray(ids));
	}
	
}
