package com.win.dfas.monitor.engine.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.win.dfas.monitor.common.constant.LineColorEnum;
import com.win.dfas.monitor.common.entity.DcDevcie;
import com.win.dfas.monitor.common.util.Convert;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.util.id.IDUtils;
import com.win.dfas.monitor.common.vo.CpuLineChartVO;
import com.win.dfas.monitor.common.vo.MetricValueVO;
import com.win.dfas.monitor.common.vo.cpu.CPULineChartMetricsResultVO;
import com.win.dfas.monitor.common.vo.jvm.JvmMemoryMetricsResultVO;
import com.win.dfas.monitor.config.mapper.DcDevcieMapper;
import com.win.dfas.monitor.engine.service.IDcDevcieService;
import com.win.dfas.monitor.engine.service.PrometheusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Autowired
	private PrometheusService prometheusService;

	/** 非千分位格式化 */
	protected NumberFormat noneThousandBitNumberFormat = NumberFormat.getNumberInstance();

	@Value("${deployment.server.url}")
	private String deploymentServerUrl;

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
		if(list != null && list.size() > 0){
			dcDevcieMapper.updateBatch(list);
		}
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

	/**
	 * 判断机器IP地址是否存在
	 *
	 * @param ip_address 机器IP地址
	 * @return
	 */
	public boolean checkIpAddressExist(String ip_address){
		return dcDevcieMapper.checkIpAddressExist(ip_address);
	}

	/**
	 * 查询机器信息
	 *
	 * @param ipAddress 机器IP
	 * @return 机器信息
	 */
	@Override
	public DcDevcie selectDcDevcieByIp(String ipAddress){
        return dcDevcieMapper.selectDcDevcieByIp(ipAddress);
	}

	/**
	 * 一键同步
	 *
	 * @return
	 */
	@Override
	public void onKeySync(){
		String url = deploymentServerUrl + "/deploy/device/list";
		String result = RestfulTools.get(url, String.class);
		Map<String,Object> map = JsonUtil.toObject(result, Map.class);
		List<Map<String,Object>> list = (ArrayList)map.get("data");
		for (Map<String,Object> dc : list){
			String ipAddress = (String)dc.get("ipAddress");
			String name = (String)dc.get("name");
			if(!checkIpAddressExist(ipAddress)){
				DcDevcie dcDevcie = new DcDevcie();
				dcDevcie.setId(IDUtils.nextId());
				dcDevcie.setIpAddress(ipAddress);
				dcDevcie.setName(name);
				insertDcDevcie(dcDevcie);
			}
		}
	}

	/**
	 * 获取变化率折线图数据
	 *
	 * @return
	 */
	@Override
	public CpuLineChartVO getCpuLineChartData(String ipAddress){
		CpuLineChartVO cpuLineChartVO = new CpuLineChartVO();
		List<CPULineChartMetricsResultVO> metricsResultList = prometheusService.getCPULineChart(ipAddress,"system");
		cpuLineChartVO.getLegendData().add("System");
		for (int i = 0; i < metricsResultList.size(); i++) {
			CPULineChartMetricsResultVO metricsResultVO = metricsResultList.get(i);
			cpuLineChartVO.setXAxisData(metricsResultVO.getAllTimesList());
			Map<String, String> metric = metricsResultVO.getMetric();
			//cpuLineChartVO.getLegendData().add(metric.get("instance"));
			//cpuLineChartVO.getColorData().add(LineColorEnum.values()[i].getColor());
			List<Double> seriesData = new ArrayList<>();
			List<MetricValueVO> values = metricsResultVO.getMetricValueList();
			if (values != null) {
				for(MetricValueVO metricValueVO:values){
					BigDecimal value = new BigDecimal(metricValueVO.getValue()).divide(new BigDecimal(1024 * 1024)).setScale(2, BigDecimal.ROUND_HALF_UP);
					seriesData.add(Double.parseDouble(noneThousandBitNumberFormat.format(value)));
				}
			}
			cpuLineChartVO.getSeriesData().add(seriesData);
		}
		return cpuLineChartVO;
	}

}
