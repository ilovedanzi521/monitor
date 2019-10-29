package com.win.dfas.monitor.engine.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.win.dfas.common.util.ObjectUtils;
import com.win.dfas.common.util.PrimaryKeyUtil;
import com.win.dfas.common.vo.WinResponseData;
import com.win.dfas.monitor.common.constant.LineColorEnum;
import com.win.dfas.monitor.common.constant.LogLevelEnum;
import com.win.dfas.monitor.common.constant.MonitorConstants;
import com.win.dfas.monitor.common.constant.StatusEnum;
import com.win.dfas.monitor.common.dto.DeployCenterMicroServiceDTO;
import com.win.dfas.monitor.common.dto.DeployCenterMicroServiceInstanceDTO;
import com.win.dfas.monitor.common.dto.MicroServiceApplicationDTO;
import com.win.dfas.monitor.common.dto.MicroServiceDTO;
import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;
import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.entity.MicroServiceInstanceEntity;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.util.StringUtils;
import com.win.dfas.monitor.common.vo.*;
import com.win.dfas.monitor.common.vo.jvm.JvmMemoryMetricsResultVO;
import com.win.dfas.monitor.config.mapper.MicroServiceInstanceMapper;
import com.win.dfas.monitor.config.mapper.MicroServiceMapper;
import com.win.dfas.monitor.engine.service.ElasticsearchService;
import com.win.dfas.monitor.engine.service.EurekaService;
import com.win.dfas.monitor.engine.service.MicroService;
import com.win.dfas.monitor.engine.service.PrometheusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MicroServiceImpl implements MicroService {

    @Autowired
    private MicroServiceMapper microServiceMapper;

    @Autowired
    private MicroServiceInstanceMapper microServiceInstanceMapper;

    @Autowired
    private PrometheusService prometheusService;

    @Autowired
    private EurekaService eurekaService;

    @PostConstruct
    void init() {
        noneThousandBitNumberFormat.setGroupingUsed(false);
    }

    @Value("${registration.center.url}")
    private String registrationCenterUrl;

    @Value("${deployment.server.url}")
    private String deploymentServerUrl;

    @Autowired
    private ElasticsearchService elasticsearchService;

    /** 非千分位格式化 */
    protected NumberFormat noneThousandBitNumberFormat = NumberFormat.getNumberInstance();

    protected NumberFormat thousandBitNumberFormat = NumberFormat.getNumberInstance();

    @Override
    public PageInfo<MicroServiceRepVO> getMicroServiceList(MicroServiceReqVO reqVO) {
        PageHelper.startPage(reqVO.getReqPageNum(), reqVO.getReqPageSize());
        MicroServiceEntity microServiceEntity = new MicroServiceEntity();
        BeanUtils.copyProperties(reqVO, microServiceEntity);
        List<MicroServiceEntity> microServiceEntityList = microServiceMapper.selectMicroServiceList(microServiceEntity);
        PageInfo<MicroServiceEntity> info = new PageInfo<>(microServiceEntityList);
        PageInfo<MicroServiceRepVO> pageList = ObjectUtils.copyPageInfo(info, MicroServiceRepVO.class);
        List<MicroServiceRepVO> microServiceRepList = pageList.getList();
        for (MicroServiceRepVO microServiceRepVO : microServiceRepList) {
            setMicroServiceInfo(microServiceRepVO);
        }
        return pageList;
    }

    private void setMicroServiceInfo(MicroServiceRepVO microServiceRepVO) {
        Map<String, ApplicationInstance> microServiceInstanceMap = eurekaService.fetchMicroService();

        List<MicroServiceInstanceEntity> microServiceInstanceList = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceRepVO.getId());
        StringBuilder sb = new StringBuilder();
        int size = microServiceInstanceList.size();
        int upCount = 0;
        for (int i = 0; i < size; i++) {
            MicroServiceInstanceEntity microServiceInstanceEntity = microServiceInstanceList.get(i);
            if (i == (size - 1)) {
                sb.append(microServiceInstanceEntity.getIpAddr());
            } else {
                sb.append(microServiceInstanceEntity.getIpAddr() + ";");
            }

            ApplicationInstance applicationInstance = microServiceInstanceMap.get(microServiceInstanceEntity.getIpAddr());
            if (applicationInstance != null) {
                if (MonitorConstants.UP_STATUS.equals(applicationInstance.getStatus())) {
                    upCount++;
                }
            }
        }
        microServiceRepVO.setIpAddress(sb.toString());

        if (microServiceInstanceList == null || upCount == 0) {
            microServiceRepVO.setState(StatusEnum.OFFLINE.getStatus());
        } else if (upCount < size) {
            microServiceRepVO.setState(StatusEnum.EXCEPTION.getStatus());
        } else if (upCount == size) {
            microServiceRepVO.setState(StatusEnum.ONLINE.getStatus());
            //继续判断JVM内存是否存在告警，存在，则设置为告警状态
        }

        try {
            Map<String, Long> bucketMap = elasticsearchService.getLogTotalCountByMicroService(microServiceRepVO.getMicroServiceName());
            long warnCount = 0;
            if (bucketMap.get(LogLevelEnum.WARN.name()) != null) {
                warnCount = bucketMap.get(LogLevelEnum.WARN.name());
            }
            long errorCount = 0;
            if (bucketMap.get(LogLevelEnum.ERROR.name()) != null) {
                errorCount = bucketMap.get(LogLevelEnum.ERROR.name());
            }
            microServiceRepVO.setWarn(String.valueOf(thousandBitNumberFormat.format(warnCount)));
            microServiceRepVO.setError(String.valueOf(thousandBitNumberFormat.format(errorCount)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MicroServiceRepVO> searchMicroService(MicroServiceReqVO microServiceReqVO) {
        MicroServiceEntity microServiceEntity = new MicroServiceEntity();
        BeanUtils.copyProperties(microServiceReqVO, microServiceEntity);
        List<MicroServiceEntity> list = microServiceMapper.selectMicroServiceList(microServiceEntity);
        return ObjectUtils.copyPropertiesList(list, MicroServiceRepVO.class);
    }

    @Override
    public List<MicroServiceRepVO> microServicePanel(MicroServiceReqVO microServiceReqVO) {
        MicroServiceEntity microServiceEntity = new MicroServiceEntity();
        BeanUtils.copyProperties(microServiceReqVO, microServiceEntity);
        List<MicroServiceEntity> list = microServiceMapper.selectMicroServiceList(microServiceEntity);
        List<MicroServiceRepVO> microServiceRepList = ObjectUtils.copyPropertiesList(list, MicroServiceRepVO.class);
        for (MicroServiceRepVO microServiceRepVO : microServiceRepList) {
            setMicroServiceInfo(microServiceRepVO);
        }
        return microServiceRepList;
    }

    @Override
    public List<MicroServiceMachineRepVO> microServiceMachineList(MicroServiceReqVO microServiceReqVO) {
        List<MicroServiceInstanceEntity> list = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceReqVO.getId());
        List<MicroServiceMachineRepVO> microServiceMachineRepList = ObjectUtils.copyPropertiesList(list, MicroServiceMachineRepVO.class);
        List<MetricsResultVO> metricsResultList = prometheusService.getJvmMemory(microServiceReqVO);
        for (MicroServiceMachineRepVO microServiceMachineRep : microServiceMachineRepList) {
            //设置机器状态
            try {
                if (metricsResultList != null) {
                    for (MetricsResultVO metricsResultVO : metricsResultList) {
                        Map<String, String> metricMap = metricsResultVO.getMetric();
                        if (metricMap.get("instance").contains(microServiceMachineRep.getIpAddr())) {
                            Double value = Double.parseDouble(String.valueOf(metricsResultVO.getValue().get(1)));
                            BigDecimal bigDecimal = new BigDecimal(value).divide(new BigDecimal(1024 * 1024)).setScale(2, BigDecimal.ROUND_HALF_UP);
                            microServiceMachineRep.setJvm(noneThousandBitNumberFormat.format(bigDecimal));
                        }
                    }
                }

                Map<String, Long> bucketMap = elasticsearchService.getLogTotalCountByMicroService(microServiceReqVO.getMicroServiceName(), microServiceMachineRep.getIpAddr(), microServiceMachineRep.getPort());
                long warnCount = 0;
                if (bucketMap.get(LogLevelEnum.WARN.name()) != null) {
                    warnCount = bucketMap.get(LogLevelEnum.WARN.name());
                }
                long errorCount = 0;
                if (bucketMap.get(LogLevelEnum.ERROR.name()) != null) {
                    errorCount = bucketMap.get(LogLevelEnum.ERROR.name());
                }
                microServiceMachineRep.setWarn(String.valueOf(thousandBitNumberFormat.format(warnCount)));
                microServiceMachineRep.setError(String.valueOf(thousandBitNumberFormat.format(errorCount)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return microServiceMachineRepList;
    }

    @Transactional
    @Override
    public void insertMicroService(MicroServiceReqVO microServiceReqVO) {
        MicroServiceEntity microServiceEntity = new MicroServiceEntity();
        BeanUtils.copyProperties(microServiceReqVO, microServiceEntity);
        microServiceEntity.setId(PrimaryKeyUtil.generateId());
        if (StringUtils.isNotEmpty(microServiceReqVO.getIpAddress())) {
            String ipAddrs[] = microServiceReqVO.getIpAddress().split(";");
            for (String ipAddr : ipAddrs) {
                MicroServiceInstanceEntity microServiceInstanceEntity = new MicroServiceInstanceEntity();
                microServiceInstanceEntity.setId(PrimaryKeyUtil.generateId());
                microServiceInstanceEntity.setServiceId(microServiceEntity.getId());
                String ips[] = ipAddr.split(":");
                if (ips.length > 0) {
                    microServiceInstanceEntity.setIpAddr(ips[0]);
                }
                if (ips.length > 1) {
                    microServiceInstanceEntity.setPort(ips[1]);
                }
                microServiceInstanceMapper.insertMicroServiceInstance(microServiceInstanceEntity);
            }
        }
        microServiceMapper.insertMicroService(microServiceEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMicroService(MicroServiceReqVO microServiceReqVO) {
        MicroServiceEntity microServiceEntity = new MicroServiceEntity();
        BeanUtils.copyProperties(microServiceReqVO, microServiceEntity);
        if (StringUtils.isNotEmpty(microServiceReqVO.getIpAddress())) {
            String ipAddresses[] = microServiceReqVO.getIpAddress().split(";");
            List<String> ipAddressList = new ArrayList<>();
            List<String> portList = new ArrayList<>();
            for (String addr : ipAddresses) {
                String ips[] = addr.split(":");
                ipAddressList.add(ips[0]);
                if (ips.length > 1) {
                    portList.add(ips[1]);
                }
            }
            List<MicroServiceInstanceEntity> instanceList = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceEntity.getId());
            List<String> existList = new ArrayList<>();
            if (instanceList != null) {
                for (MicroServiceInstanceEntity microServiceInstanceEntity : instanceList) {
                    if (!ipAddressList.contains(microServiceInstanceEntity.getIpAddr())) {
                        microServiceInstanceMapper.deleteMicroServiceInstance(microServiceInstanceEntity.getId());
                    } else {
                        existList.add(microServiceInstanceEntity.getIpAddr());
                    }
                }
            }
            for (String addr : ipAddresses) {
                String ips[] = addr.split(":");
                if (!existList.contains(addr)) {
                    MicroServiceInstanceEntity microServiceInstanceEntity = new MicroServiceInstanceEntity();
                    microServiceInstanceEntity.setId(PrimaryKeyUtil.generateId());
                    microServiceInstanceEntity.setServiceId(microServiceEntity.getId());
                    microServiceInstanceEntity.setApp(microServiceEntity.getMicroServiceName());
                    microServiceInstanceEntity.setIpAddr(ips[0]);
                    if (ips.length > 1) {
                        microServiceInstanceEntity.setPort(ips[1]);
                    }
                    microServiceInstanceMapper.insertMicroServiceInstance(microServiceInstanceEntity);
                }
            }
        } else {
            microServiceInstanceMapper.deleteMicroServiceInstanceByServiceId(microServiceEntity.getId());
        }
        microServiceMapper.updateMicroService(microServiceEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMicroService(Long id) {
        microServiceMapper.deleteMicroService(id);
        microServiceInstanceMapper.deleteMicroServiceInstanceByServiceId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMicroServiceByIds(String ids) {
        String strIds[] = ids.split("_");
        List<Long> longIds = new ArrayList<>();
        for (int i = 0; i < strIds.length; i++) {
            longIds.add(Long.parseLong(strIds[i]));
        }
        microServiceMapper.deleteMicroServiceByIds(longIds);
        for (int i = 0; i < longIds.size(); i++) {
            microServiceInstanceMapper.deleteMicroServiceInstanceByServiceId(longIds.get(i));
        }
    }

    private List<MicroServiceApplicationDTO> getApplicationList() {
        StringBuilder url = new StringBuilder();
        if (registrationCenterUrl.endsWith("/")) {
            url.append(registrationCenterUrl + "eureka/apps");
        } else {
            url.append(registrationCenterUrl + "/eureka/apps");
        }
        String result = RestfulTools.get(url.toString(), String.class);
        MicroServiceDTO microService = JsonUtil.toObject(result, MicroServiceDTO.class);
        return microService.getApplications().getApplication();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void synchronizeMicroService() {
        this.microServiceMapper.clearMicroService();
        this.microServiceInstanceMapper.clearMicroServiceInstance();
        syncFromDeployCenter();
    }


    private void syncFromEureka() {
        List<MicroServiceApplicationDTO> applicationList = getApplicationList();
        if (applicationList != null) {
            List<MicroServiceEntity> microServiceEntityList = new ArrayList<>();
            List<MicroServiceInstanceEntity> microServiceInstanceEntityList = new ArrayList<>();
            for (MicroServiceApplicationDTO microServiceApplicationDTO : applicationList) {

                MicroServiceEntity microServiceEntity = new MicroServiceEntity();
                microServiceEntity.setId(PrimaryKeyUtil.generateId());
                microServiceEntity.setMicroServiceName(microServiceApplicationDTO.getName());
                microServiceEntityList.add(microServiceEntity);

                List<ApplicationInstance> instanceList = microServiceApplicationDTO.getInstance();
                if (instanceList != null) {
                    for (ApplicationInstance instance : instanceList) {
                        MicroServiceInstanceEntity microServiceInstanceEntity = new MicroServiceInstanceEntity();
                        microServiceInstanceEntity.setId(PrimaryKeyUtil.generateId());
                        microServiceInstanceEntity.setServiceId(microServiceEntity.getId());
                        microServiceInstanceEntity.setInstanceId(instance.getInstanceId());
                        microServiceInstanceEntity.setHostName(instance.getHostName());
                        microServiceInstanceEntity.setApp(instance.getApp());
                        microServiceInstanceEntity.setIpAddr(instance.getIpAddr());
                        Object object = instance.getPort();
                        Map<String, Integer> map = (LinkedHashMap) object;
                        microServiceInstanceEntity.setPort(String.valueOf(map.get("$")));
                        microServiceInstanceEntityList.add(microServiceInstanceEntity);
                    }
                }
            }
            if (microServiceInstanceEntityList.size() > 0) {
                microServiceInstanceMapper.insertMicroServiceInstanceList(microServiceInstanceEntityList);
            }
            if (microServiceEntityList.size() > 0) {
                microServiceMapper.insertMicroServiceList(microServiceEntityList);
            }
        }
    }

    private void syncFromDeployCenter() {
        String url = deploymentServerUrl + "/deploy/app/module/treeList";
        String result = RestfulTools.get(url, String.class);
        WinResponseData winResponseData = JsonUtil.toObject(result, WinResponseData.class);
        if (winResponseData.getData() != null) {
            String data = winResponseData.getData().toString();
            List<DeployCenterMicroServiceDTO> deployCenterMicroServiceDTOList = (List<DeployCenterMicroServiceDTO>) JsonUtil.toList(data, DeployCenterMicroServiceDTO.class);

            List<MicroServiceEntity> microServiceEntityList = new ArrayList<>();
            List<MicroServiceInstanceEntity> microServiceInstanceEntityList = new ArrayList<>();

            for (DeployCenterMicroServiceDTO deployCenterMicroServiceDTO : deployCenterMicroServiceDTOList) {
                MicroServiceEntity microServiceEntity = new MicroServiceEntity();
                microServiceEntity.setId(PrimaryKeyUtil.generateId());
                microServiceEntity.setMicroServiceName(deployCenterMicroServiceDTO.getMicroServiceName());
                microServiceEntityList.add(microServiceEntity);

                List<DeployCenterMicroServiceInstanceDTO> instanceList = deployCenterMicroServiceDTO.getInstanceList();
                if (instanceList != null) {
                    for (DeployCenterMicroServiceInstanceDTO deployCenterMicroServiceInstanceDTO : instanceList) {
                        MicroServiceInstanceEntity microServiceInstanceEntity = new MicroServiceInstanceEntity();
                        microServiceInstanceEntity.setId(PrimaryKeyUtil.generateId());
                        microServiceInstanceEntity.setServiceId(microServiceEntity.getId());
                        microServiceInstanceEntity.setHostName(deployCenterMicroServiceInstanceDTO.getHostName());
                        microServiceInstanceEntity.setIpAddr(deployCenterMicroServiceInstanceDTO.getIpAddr());
                        microServiceInstanceEntityList.add(microServiceInstanceEntity);
                    }
                }
            }

            if (microServiceInstanceEntityList.size() > 0) {
                microServiceInstanceMapper.insertMicroServiceInstanceList(microServiceInstanceEntityList);
            }
            if (microServiceEntityList.size() > 0) {
                microServiceMapper.insertMicroServiceList(microServiceEntityList);
            }
        } else {
            syncFromEureka();
        }
    }

    @Override
    public MicroServiceRepVO microServiceInfo(MicroServiceReqVO reqVO) {
        MicroServiceEntity microServiceEntity = microServiceMapper.selectMicroService(reqVO.getId());
        MicroServiceRepVO microServiceRepVO = new MicroServiceRepVO();
        BeanUtils.copyProperties(microServiceEntity, microServiceRepVO);
        setMicroServiceInfo(microServiceRepVO);
        return microServiceRepVO;
    }

    @Override
    public MicroServiceJvmMemoryVO jvmMemory(MicroServiceReqVO reqVO) {
        MicroServiceJvmMemoryVO microServiceJvmMemory = new MicroServiceJvmMemoryVO();
        List<JvmMemoryMetricsResultVO> metricsResultList = prometheusService.getJvmMemoryChart(reqVO);
        for (int i = 0; i < metricsResultList.size(); i++) {
            JvmMemoryMetricsResultVO metricsResultVO = metricsResultList.get(i);
            microServiceJvmMemory.setXAxisData(metricsResultVO.getAllTimesList());
            Map<String, String> metric = metricsResultVO.getMetric();
            microServiceJvmMemory.getLegendData().add(metric.get("instance"));
            microServiceJvmMemory.getColorData().add(LineColorEnum.values()[i].getColor());
            List<Double> seriesData = new ArrayList<>();
            List<MetricValueVO> values = metricsResultVO.getMetricValueList();
            if (values != null) {
                for (MetricValueVO metricValueVO : values) {
                    BigDecimal value = new BigDecimal(metricValueVO.getValue()).divide(new BigDecimal(1024 * 1024)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    seriesData.add(Double.parseDouble(noneThousandBitNumberFormat.format(value)));
                }
            }
            microServiceJvmMemory.getSeriesData().add(seriesData);
        }

        return microServiceJvmMemory;
    }


}
