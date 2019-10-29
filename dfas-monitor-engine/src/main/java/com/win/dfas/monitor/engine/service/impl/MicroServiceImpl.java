package com.win.dfas.monitor.engine.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.win.dfas.common.util.ObjectUtils;
import com.win.dfas.common.util.PrimaryKeyUtil;
import com.win.dfas.monitor.common.constant.LineColorEnum;
import com.win.dfas.monitor.common.constant.LogLevelEnum;
import com.win.dfas.monitor.common.constant.MonitorConstants;
import com.win.dfas.monitor.common.constant.StatusEnum;
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
import java.util.*;

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

        Map<String, ApplicationInstance> microServiceInstanceMap = eurekaService.fetchMicroService();

        for (MicroServiceRepVO microServiceRepVO : microServiceRepList) {
            List<MicroServiceInstanceEntity> microServiceInstanceList = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceRepVO.getId());
            StringBuilder sb = new StringBuilder();
            int size = microServiceInstanceList.size();
            int upCount = 0;
            for (int i = 0; i < size; i++) {
                MicroServiceInstanceEntity microServiceInstanceEntity = microServiceInstanceList.get(i);
                if (i == (size - 1)) {
                    sb.append(microServiceInstanceEntity.getIpAddr() );
                } else {
                    sb.append(microServiceInstanceEntity.getIpAddr() );
                }

                ApplicationInstance applicationInstance = microServiceInstanceMap.get(microServiceInstanceEntity.getIpAddr());
                if (applicationInstance != null) {
                    if (MonitorConstants.UP_STATUS.equals(applicationInstance.getStatus())) {
                        upCount++;
                    }
                }

            }
            microServiceRepVO.setIpAddress(sb.toString());

            if(microServiceInstanceList == null  ||  upCount ==0) {
                microServiceRepVO.setState(StatusEnum.OFFLINE.getStatus());
            }else if(upCount  < size){
                microServiceRepVO.setState(StatusEnum.EXCEPTION.getStatus());
            }else if(upCount ==size){
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
        return pageList;
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
        return ObjectUtils.copyPropertiesList(list, MicroServiceRepVO.class);
    }

    @Override
    public List<MicroServiceMachineRepVO> microServiceMachineList(MicroServiceReqVO microServiceReqVO) {
        List<MicroServiceInstanceEntity> list = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceReqVO.getId());
        List<MicroServiceMachineRepVO> microServiceMachineRepList = ObjectUtils.copyPropertiesList(list, MicroServiceMachineRepVO.class);
        Map<String, ApplicationInstance> microServiceMap = fetchMicroService();
        for (MicroServiceMachineRepVO microServiceMachineRep : microServiceMachineRepList) {
            //设置机器状态、JVM内存
            try {
               String result= prometheusService.getJvmMemory(microServiceReqVO);

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
                        existList.add(microServiceInstanceEntity.getIpAddr() );
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
        String url = registrationCenterUrl + "/eureka/apps";
        String result = RestfulTools.get(url, String.class);
        MicroServiceDTO microService = JsonUtil.toObject(result, MicroServiceDTO.class);
        return microService.getApplications().getApplication();
    }

    private Map<String, ApplicationInstance> fetchMicroService() {
        Map<String, ApplicationInstance> microServiceMap = new HashMap<>();
        List<MicroServiceApplicationDTO> applicationList = getApplicationList();
        if (applicationList != null) {
            for (MicroServiceApplicationDTO microServiceApplicationDTO : applicationList) {
                List<ApplicationInstance> instanceList = microServiceApplicationDTO.getInstance();
                if (instanceList != null) {
                    for (ApplicationInstance instance : instanceList) {
                        microServiceMap.put(instance.getInstanceId(), instance);
                    }
                }
            }
        }
        return microServiceMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void synchronizeMicroService() {
        this.microServiceMapper.clearMicroService();
        this.microServiceInstanceMapper.clearMicroServiceInstance();
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

    @Override
    public MicroServiceRepVO microServiceInfo(MicroServiceReqVO reqVO) {
        MicroServiceEntity microServiceEntity = microServiceMapper.selectMicroService(reqVO.getId());
        MicroServiceRepVO microServiceRepVO = new MicroServiceRepVO();
        BeanUtils.copyProperties(microServiceEntity, microServiceRepVO);
        Map<String, ApplicationInstance> microServiceInstanceMap = fetchMicroService();
        List<MicroServiceInstanceEntity> instanceList = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceRepVO.getId());
        int upCount = 0;
        if (instanceList != null) {
            for (MicroServiceInstanceEntity microServiceInstanceEntity : instanceList) {
                ApplicationInstance applicationInstance = microServiceInstanceMap.get(microServiceInstanceEntity.getIpAddr());
                if (applicationInstance != null) {
                    if (MonitorConstants.UP_STATUS.equals(applicationInstance.getStatus())) {
                        upCount++;
                    }
                }
            }
        }
        if (instanceList == null || upCount == 0) {
            microServiceRepVO.setState(StatusEnum.OFFLINE.getStatus());
        } else if (upCount < instanceList.size()) {
            microServiceRepVO.setState(StatusEnum.EXCEPTION.getStatus());
        } else if (upCount == instanceList.size()) {
            microServiceRepVO.setState(StatusEnum.ONLINE.getStatus());
            //继续判断JVM内存是否存在告警，存在，则设置为告警状态
        }

        try {
            Map<String, Long> bucketMap = elasticsearchService.getLogTotalCountByMicroService(reqVO.getMicroServiceName());
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
