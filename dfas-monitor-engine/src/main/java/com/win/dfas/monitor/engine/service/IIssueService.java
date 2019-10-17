package com.win.dfas.monitor.engine.service;

import com.win.dfas.monitor.common.entity.Issue;

import java.util.List;

/**
 *  服务层
 * 
 * @author lj
 * @date 2019-10-17
 */
public interface IIssueService 
{
	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
	public Issue selectIssueById(String id);
	
	/**
     * 查询列表
     * 
     * @param issue 信息
     * @return 集合
     */
	public List<Issue> selectIssueList(Issue issue);
	
	/**
     * 新增
     * 
     * @param issue 信息
     * @return 结果
     */
	public int insertIssue(Issue issue);
	
	/**
     * 修改
     * 
     * @param issue 信息
     * @return 结果
     */
	public int updateIssue(Issue issue);
		
	/**
     * 删除信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIssueByIds(String ids);
	
}
