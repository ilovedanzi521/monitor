package com.win.dfas.monitor.engine.service.impl;

import java.util.List;

import com.win.dfas.monitor.common.entity.Issue;
import com.win.dfas.monitor.common.util.Convert;
import com.win.dfas.monitor.config.mapper.IssueMapper;
import com.win.dfas.monitor.engine.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  服务层实现
 * 
 * @author lj
 * @date 2019-10-17
 */
@Service
public class IssueServiceImpl implements IIssueService
{
	@Autowired
	private IssueMapper issueMapper;

	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
    @Override
	public Issue selectIssueById(String id)
	{
	    return issueMapper.selectIssueById(id);
	}
	
	/**
     * 查询列表
     * 
     * @param issue 信息
     * @return 集合
     */
	@Override
	public List<Issue> selectIssueList(Issue issue)
	{
	    return issueMapper.selectIssueList(issue);
	}
	
    /**
     * 新增
     * 
     * @param issue 信息
     * @return 结果
     */
	@Override
	public int insertIssue(Issue issue)
	{
	    return issueMapper.insertIssue(issue);
	}
	
	/**
     * 修改
     * 
     * @param issue 信息
     * @return 结果
     */
	@Override
	public int updateIssue(Issue issue)
	{
	    return issueMapper.updateIssue(issue);
	}

	/**
     * 删除对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIssueByIds(String ids)
	{
		return issueMapper.deleteIssueByIds(Convert.toStrArray(ids));
	}
	
}
