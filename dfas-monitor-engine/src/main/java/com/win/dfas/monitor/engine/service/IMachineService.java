package com.win.dfas.monitor.engine.service;

import com.win.dfas.monitor.common.entity.Machine;
import com.win.dfas.monitor.common.vo.CpuLineChartVO;
import com.win.dfas.monitor.common.vo.DiskBarChartVO;

import java.util.List;

/**
 * 机器 服务层
 * 
 * @author lj
 * @date 2019-10-10
 */
public interface IMachineService
{
	/**
     * 查询机器信息
     * 
     * @param id 机器ID
     * @return 机器信息
     */
	public Machine selectDcDevcieById(String id);
	/**
	 * 查询机器信息
	 *
	 * @param ip 机器IP
	 * @return 机器信息
	 */
	public Machine selectDcDevcieByIp(String ip);
	
	/**
     * 查询机器列表
     * 
     * @param dcDevcie 机器信息
     * @return 机器集合
     */
	public List<Machine> selectDcDevcieList(Machine dcDevcie);
	
	/**
     * 新增机器
     * 
     * @param dcDevcie 机器信息
     * @return 结果
     */
	public int insertDcDevcie(Machine dcDevcie);
	
	/**
     * 修改机器
     * 
     * @param dcDevcie 机器信息
     * @return 结果
     */
	public int updateDcDevcie(Machine dcDevcie);

	/**
	 * 批量修改机器
	 *
	 * @param list 机器信息
	 * @return 结果
	 */
	public void updateBatch(List<Machine> list);
		
	/**
     * 删除机器信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDcDevcieByIds(String ids);

	/**
	 * 获取总结点数
	 *
	 * @return 结果
	 */
	public int getTotalNode();

	/**
	 * 一键同步
	 *
	 * @return
	 */
	public void onKeySync();
	/**
	 * 获取变化率折线图数据
	 *
	 * @return
	 */
	public CpuLineChartVO getCpuLineChartData(String ipAddress);
	/**
	 * 获取磁盘使用占比图数据
	 *
	 * @return
	 */
	public DiskBarChartVO getDiskBarChartData(String ipAddress);
	
}
