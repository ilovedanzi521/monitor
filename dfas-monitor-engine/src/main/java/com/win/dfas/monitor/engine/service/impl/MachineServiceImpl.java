package com.win.dfas.monitor.engine.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.win.dfas.monitor.common.constant.LineColorEnum;
import com.win.dfas.monitor.common.constant.MonitorConstants;
import com.win.dfas.monitor.common.entity.Machine;
import com.win.dfas.monitor.common.util.Convert;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.util.id.IDUtils;
import com.win.dfas.monitor.common.vo.CpuLineChartVO;
import com.win.dfas.monitor.common.vo.DiskBarChartVO;
import com.win.dfas.monitor.common.vo.MetricValueVO;
import com.win.dfas.monitor.common.vo.cpu.CPULineChartMetricsResultVO;
import com.win.dfas.monitor.common.vo.cpu.CPUSeriesDataVO;
import com.win.dfas.monitor.common.vo.disk.DiskBarChartMetricsResultVO;
import com.win.dfas.monitor.common.vo.disk.DiskMetricVO;
import com.win.dfas.monitor.common.vo.disk.DiskSeriesDataVO;
import com.win.dfas.monitor.config.mapper.MachineMapper;
import com.win.dfas.monitor.engine.service.IMachineService;
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
public class MachineServiceImpl implements IMachineService
{
	@Autowired
	private MachineMapper dcDevcieMapper;

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
	public Machine selectDcDevcieById(String id)
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
	public List<Machine> selectDcDevcieList(Machine dcDevcie)
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
	public int insertDcDevcie(Machine dcDevcie)
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
	public int updateDcDevcie(Machine dcDevcie)
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
	public void updateBatch(List<Machine> list){
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
	public Machine selectDcDevcieByIp(String ipAddress){
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
				Machine dcDevcie = new Machine();
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
		cpuLineChartVO.getColorData().add("#33CC33");
		/*cpuLineChartVO.getColorData().add("#FF4D4D");
		cpuLineChartVO.getColorData().add("#00BAF3");
		cpuLineChartVO.getColorData().add("#2f4cbd");*/
		setCpuLineChartData(ipAddress,cpuLineChartVO,"system","System");
		/*setCpuLineChartData(ipAddress,cpuLineChartVO,"user","User");
		setCpuLineChartData(ipAddress,cpuLineChartVO,"idle","Idel");
		setCpuLineChartData(ipAddress,cpuLineChartVO,"iowait","Iowait");*/
		return cpuLineChartVO;
	}

	/**
	 * 获取磁盘使用占比图数据
	 *
	 * @return
	 */
	@Override
	public DiskBarChartVO getDiskBarChartData(String ipAddress){
		DiskBarChartVO diskBarChartVO = new DiskBarChartVO();
		setDiskBarChartData(ipAddress,diskBarChartVO,MonitorConstants.DISK_USED,"已使用");
		setDiskBarChartData(ipAddress,diskBarChartVO,MonitorConstants.DISK_FREE,"未使用");
		return diskBarChartVO;
	}

	private void setDiskBarChartData(String ipAddress, DiskBarChartVO diskBarChartVO, String type, String lengData) {
		List<DiskBarChartMetricsResultVO> metricsResultList = prometheusService.getDiskBarChart(ipAddress, type);
		diskBarChartVO.getLegendData().add(lengData);
		DiskSeriesDataVO diskSeriesDataVO = new DiskSeriesDataVO();
		diskSeriesDataVO.setName(lengData);
		diskSeriesDataVO.setType("bar");
		List<Double> seriesData = new ArrayList<>();
		for (int i = 0; i < metricsResultList.size(); i++) {
			DiskBarChartMetricsResultVO metricsResultVO = metricsResultList.get(i);
			DiskMetricVO diskMetricVO = metricsResultVO.getMetric();
			if(!diskBarChartVO.getYAxisData().contains(diskMetricVO.getMountpoint())){
				diskBarChartVO.getYAxisData().add(diskMetricVO.getMountpoint());
			}
			List<Object> values = metricsResultVO.getValue();
			if (values != null) {
				seriesData.add(formatSize(String.valueOf(values.get(1))));
			}
		}
		diskSeriesDataVO.setData(seriesData);
		diskBarChartVO.getSeriesData().add(diskSeriesDataVO);
	}

	public static double formatSize(String value) {
		long size = Long.parseLong(value);
		double hrSize = 0;
		double b = size;
		double k = size / 1024.0;
		double m = ((size / 1024.0) / 1024.0);
		double g = (((size / 1024.0) / 1024.0) / 1024.0);
		double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);
		if (t > 1) {
			hrSize =  t;
		} else if (g > 1) {
			hrSize =  g;
		} else if (m > 1) {
			hrSize =  m;
		} else if (k > 1) {
			hrSize =  k;
		} else {
			hrSize =  b;
		}
		return hrSize;
	}

	private void setCpuLineChartData(String ipAddress, CpuLineChartVO cpuLineChartVO, String type, String lengData) {
		List<CPULineChartMetricsResultVO> metricsResultList = prometheusService.getCPULineChart(ipAddress,type);
		cpuLineChartVO.getLegendData().add(lengData);
		CPUSeriesDataVO cpuSeriesDataVO = new CPUSeriesDataVO();
		cpuSeriesDataVO.setName(lengData);
		cpuSeriesDataVO.setType("line");
		cpuSeriesDataVO.setStack("总量");
		List<Double> seriesData = new ArrayList<>();
		for (int i = 0; i < metricsResultList.size(); i++) {
			CPULineChartMetricsResultVO metricsResultVO = metricsResultList.get(i);
			cpuLineChartVO.setXAxisData(metricsResultVO.getAllTimesList());
			Map<String, String> metric = metricsResultVO.getMetric();
			//cpuLineChartVO.getLegendData().add(metric.get("instance"));
			//cpuLineChartVO.getColorData().add(LineColorEnum.values()[i].getColor());
			List<MetricValueVO> values = metricsResultVO.getMetricValueList();
			if (values != null) {
				for(MetricValueVO metricValueVO:values){
					//BigDecimal value = new BigDecimal(metricValueVO.getValue()).divide(new BigDecimal(1024 * 1024)).setScale(2, BigDecimal.ROUND_HALF_UP);
					//seriesData.add(Double.parseDouble(noneThousandBitNumberFormat.format(value)));
					seriesData.add(Double.parseDouble(metricValueVO.getValue()) * 100);
				}
			}
		}
		cpuSeriesDataVO.setData(seriesData);
		cpuLineChartVO.getSeriesData().add(cpuSeriesDataVO);
	}

}
