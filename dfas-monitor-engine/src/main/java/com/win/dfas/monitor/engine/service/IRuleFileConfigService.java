package com.win.dfas.monitor.engine.service;

import com.win.dfas.monitor.common.entity.RuleFileConfig;
import java.util.List;

/**
 *  服务层
 * 
 * @author yss
 * @date 2019-10-29
 */
public interface IRuleFileConfigService 
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
     * 删除信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRuleFileConfigByIds(String ids);
	
}
