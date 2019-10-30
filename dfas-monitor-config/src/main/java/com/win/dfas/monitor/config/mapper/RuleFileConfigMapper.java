package com.win.dfas.monitor.config.mapper;

import com.win.dfas.monitor.common.entity.RuleFileConfig;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *  数据层
 * 
 * @author lj
 * @date 2019-10-29
 */
@Repository
public interface RuleFileConfigMapper 
{
	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
	public RuleFileConfig selectRuleFileConfigById(String id);
	
	/**
     * 查询列表
     * 
     * @param ruleFileConfig 信息
     * @return 集合
     */
	public List<RuleFileConfig> selectRuleFileConfigList(RuleFileConfig ruleFileConfig);
	
	/**
     * 新增
     * 
     * @param ruleFileConfig 信息
     * @return 结果
     */
	public int insertRuleFileConfig(RuleFileConfig ruleFileConfig);
	
	/**
     * 修改
     * 
     * @param ruleFileConfig 信息
     * @return 结果
     */
	public int updateRuleFileConfig(RuleFileConfig ruleFileConfig);
	
	/**
     * 删除
     * 
     * @param id ID
     * @return 结果
     */
	public int deleteRuleFileConfigById(String id);
	
	/**
     * 批量删除
     * 
     * @param iDs 需要删除的数据ID
     * @return 结果
     */
	public int deleteRuleFileConfigByIds(String[] iDs);
	
}