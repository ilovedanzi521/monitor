package com.win.dfas.monitor.config.mapper;

import com.win.dfas.monitor.common.entity.Threshold;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  阈值 数据层
 * 
 * @author lj
 * @date 2019-10-18
 */
@Repository
public interface ThresholdMapper 
{
	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
	public Threshold selectThresholdById(String id);

	/**
	 * 查询信息
	 *
	 * @param indicatorBody
	 * @return 信息
	 */
	public Threshold selectThresholdByIndicatorBody(String indicatorBody);
	
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
     * 删除
     * 
     * @param id ID
     * @return 结果
     */
	public int deleteThresholdById(String id);
	
	/**
     * 批量删除
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteThresholdByIds(String[] ids);
	
}