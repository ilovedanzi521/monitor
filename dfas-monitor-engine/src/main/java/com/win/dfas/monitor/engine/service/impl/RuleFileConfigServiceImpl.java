package com.win.dfas.monitor.engine.service.impl;

import java.util.List;
import com.win.dfas.monitor.common.entity.RuleFileConfig;
import com.win.dfas.monitor.common.util.Convert;
import com.win.dfas.monitor.config.mapper.RuleFileConfigMapper;
import com.win.dfas.monitor.engine.service.IRuleFileConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *  服务层实现
 * 
 * @author lj
 * @date 2019-10-29
 */
@Service
public class RuleFileConfigServiceImpl implements IRuleFileConfigService
{
	@Autowired
	private RuleFileConfigMapper ruleFileConfigMapper;

	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
    @Override
	public RuleFileConfig selectRuleFileConfigById(String id)
	{
	    return ruleFileConfigMapper.selectRuleFileConfigById(id);
	}
	
	/**
     * 查询列表
     * 
     * @param ruleFileConfig 信息
     * @return 集合
     */
	@Override
	public List<RuleFileConfig> selectRuleFileConfigList(RuleFileConfig ruleFileConfig)
	{
	    return ruleFileConfigMapper.selectRuleFileConfigList(ruleFileConfig);
	}
	
    /**
     * 新增
     * 
     * @param ruleFileConfig 信息
     * @return 结果
     */
	@Override
	public int insertRuleFileConfig(RuleFileConfig ruleFileConfig)
	{
	    return ruleFileConfigMapper.insertRuleFileConfig(ruleFileConfig);
	}
	
	/**
     * 修改
     * 
     * @param ruleFileConfig 信息
     * @return 结果
     */
	@Override
	public int updateRuleFileConfig(RuleFileConfig ruleFileConfig)
	{
	    return ruleFileConfigMapper.updateRuleFileConfig(ruleFileConfig);
	}

	/**
     * 删除对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRuleFileConfigByIds(String ids)
	{
		return ruleFileConfigMapper.deleteRuleFileConfigByIds(Convert.toStrArray(ids));
	}
	
}
