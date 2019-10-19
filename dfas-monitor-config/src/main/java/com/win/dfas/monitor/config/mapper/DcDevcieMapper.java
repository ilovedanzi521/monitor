package com.win.dfas.monitor.config.mapper;

import com.win.dfas.monitor.common.entity.DcDevcie;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 机器 数据层
 * 
 * @author lj
 * @date 2019-10-10
 */
@Repository
public interface DcDevcieMapper 
{
	/**
     * 查询机器信息
     * 
     * @param id 机器ID
     * @return 机器信息
     */
	public DcDevcie selectDcDevcieById(String id);

	/**
	 * 判断机器IP地址是否存在
	 *
	 * @param ip_address 机器IP地址
	 * @return
	 */
	public boolean checkIpAddressExist(String ip_address);

	
	/**
     * 查询机器列表
     * 
     * @param dcDevcie 机器信息
     * @return 机器集合
     */
	public List<DcDevcie> selectDcDevcieList(DcDevcie dcDevcie);
	
	/**
     * 新增机器
     * 
     * @param dcDevcie 机器信息
     * @return 结果
     */
	public int insertDcDevcie(DcDevcie dcDevcie);

	/**
	 * 获取总结点数
	 *
	 * @return 结果
	 */
	public int getTotalNode();
	/**
     * 修改机器
     * 
     * @param dcDevcie 机器信息
     * @return 结果
     */
	public int updateDcDevcie(DcDevcie dcDevcie);



	/**
	 * 批量修改机器
	 *
	 * @param list 机器信息
	 * @return 结果
	 */
	public void updateBatch(List<DcDevcie> list);
	
	/**
     * 删除机器
     * 
     * @param id 机器ID
     * @return 结果
     */
	public int deleteDcDevcieById(String id);
	
	/**
     * 批量删除机器
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDcDevcieByIds(String[] ids);
	
}