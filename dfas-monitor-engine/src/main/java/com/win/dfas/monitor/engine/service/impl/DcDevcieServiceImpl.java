package com.win.dfas.monitor.engine.service.impl;

import java.util.List;

import com.win.dfas.monitor.common.entity.DcDevcie;
import com.win.dfas.monitor.common.util.Convert;
import com.win.dfas.monitor.config.mapper.DcDevcieMapper;
import com.win.dfas.monitor.engine.service.IDcDevcieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 机器 服务层实现
 * 
 * @author lj
 * @date 2019-10-10
 */
@Service
public class DcDevcieServiceImpl implements IDcDevcieService
{
	@Autowired
	private DcDevcieMapper dcDevcieMapper;

	/**
     * 查询机器信息
     * 
     * @param id 机器ID
     * @return 机器信息
     */
    @Override
	public DcDevcie selectDcDevcieById(String id)
	{
	    return dcDevcieMapper.selectDcDevcieById(id);
	}
	
	/**
     * 查询机器列表
     * 
     * @param dcDevcie 机器信息
     * @return 机器集合
     */
	@Override
	public List<DcDevcie> selectDcDevcieList(DcDevcie dcDevcie)
	{
	    return dcDevcieMapper.selectDcDevcieList(dcDevcie);
	}
	
    /**
     * 新增机器
     * 
     * @param dcDevcie 机器信息
     * @return 结果
     */
	@Override
	public int insertDcDevcie(DcDevcie dcDevcie)
	{
	    return dcDevcieMapper.insertDcDevcie(dcDevcie);
	}
	
	/**
     * 修改机器
     * 
     * @param dcDevcie 机器信息
     * @return 结果
     */
	@Override
	public int updateDcDevcie(DcDevcie dcDevcie)
	{
	    return dcDevcieMapper.updateDcDevcie(dcDevcie);
	}

	/**
	 * 批量修改机器
	 *
	 * @param list 机器信息
	 * @return 结果
	 */
	@Override
	public void updateBatch(List<DcDevcie> list){
		dcDevcieMapper.updateBatch(list);
	}

	/**
     * 删除机器对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDcDevcieByIds(String ids)
	{
		return dcDevcieMapper.deleteDcDevcieByIds(Convert.toStrArray(ids));
	}

	/**
	 * 获取总结点数
	 *
	 * @return 结果
	 */
	@Override
	public int getTotalNode(){
		return dcDevcieMapper.getTotalNode();
	}

	
}
