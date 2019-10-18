package com.win.dfas.monitor.engine.service;

import com.win.dfas.monitor.common.entity.Threshold;

import java.util.List;

/**
 *  服务层
 * 
 * @author yss
 * @date 2019-10-18
 */
public interface IThresholdService 
{
	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
	public Threshold selectThresholdById(String id);
	
	/**
     * 查询列表
     * 
     * @param threshold 信息
     * @return 集合
     */
	public List<Threshold> selectThresholdList(Threshold threshold);
	
	/**
     * 新增
     * 
     * @param threshold 信息
     * @return 结果
     */
	public int insertThreshold(Threshold threshold);
	
	/**
     * 修改
     * 
     * @param threshold 信息
     * @return 结果
     */
	public int updateThreshold(Threshold threshold);
		
	/**
     * 删除信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteThresholdByIds(String ids);
	
}
