package com.win.dfas.monitor.engine.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.win.dfas.common.util.ObjectUtils;
import com.win.dfas.common.util.PrimaryKeyUtil;
import com.win.dfas.monitor.common.constant.LineColorEnum;
import com.win.dfas.monitor.common.constant.MonitorConstants;
import com.win.dfas.monitor.common.dto.MicroServiceApplicationDTO;
import com.win.dfas.monitor.common.dto.MicroServiceDTO;
import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;
import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.util.StringUtils;
import com.win.dfas.monitor.common.vo.*;
import com.win.dfas.monitor.config.mapper.MicroServiceMapper;
import com.win.dfas.monitor.engine.service.EurekaService;
import com.win.dfas.monitor.engine.service.MicroService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MicroServiceImpl implements MicroService {

    @Autowired
    private MicroServiceMapper microServiceMapper;

    @Value("${prometheus.server.url}")
    private String prometheusServerUrl;

    @Value("${registration.center.url}")
    private String registrationCenterUrl;


    @Override
    public PageInfo<MicroServiceRepVO> getMicroServiceList(MicroServiceReqVO reqVO) {
        PageHelper.startPage(reqVO.getReqPageNum(), reqVO.getReqPageSize());
        List<MicroServiceEntity> list = microServiceMapper.selectMicroServiceList(reqVO);
        for (MicroServiceEntity microServiceEntity : list) {
            if (StringUtils.isEmpty(microServiceEntity.getMicroServiceAlias())) {
                microServiceEntity.setMicroServiceAlias(MonitorConstants.DEFAULT_DISPLAY_SYMBOL);
            }
        }
        PageInfo<MicroServiceEntity> info = new PageInfo<>(list);
        return ObjectUtils.copyPageInfo(info, MicroServiceRepVO.class);
    }

    @Override
    public List<MicroServiceRepVO> searchMicroService(MicroServiceReqVO microServiceReqVO) {
        List<MicroServiceEntity> list = microServiceMapper.searchMicroService(microServiceReqVO);
        return ObjectUtils.copyPropertiesList(list, MicroServiceRepVO.class);
    }

    @Override
    public List<MicroServiceMachineRepVO> microServiceMachineList(MicroServiceReqVO microServiceReqVO) {
        List<MicroServiceEntity> list = microServiceMapper.microServiceMachineList(microServiceReqVO);
        List<MicroServiceMachineRepVO> microServiceMachineRepList = ObjectUtils.copyPropertiesList(list, MicroServiceMachineRepVO.class);
        Map<String, ApplicationInstance> microServiceMap = fetchMicroService();
        for (MicroServiceMachineRepVO microServiceMachineRep : microServiceMachineRepList) {
            ApplicationInstance applicationInstance = microServiceMap.get(microServiceMachineRep.getInstanceId());
            if (applicationInstance != null) {
                microServiceMachineRep.setState(applicationInstance.getStatus());
            }
        }
        return microServiceMachineRepList;
    }

    @Override
    public void insertMicroService(MicroServiceReqVO microServiceReqVO) {
        MicroServiceEntity microServiceEntity = new MicroServiceEntity();
        BeanUtils.copyProperties(microServiceReqVO, microServiceEntity);
        microServiceEntity.setId(PrimaryKeyUtil.generateId());
        microServiceMapper.insertMicroService(microServiceEntity);
    }

    @Override
    public void updateMicroService(MicroServiceReqVO microServiceReqVO) {
        MicroServiceEntity microServiceEntity = new MicroServiceEntity();
        BeanUtils.copyProperties(microServiceReqVO, microServiceEntity);
        microServiceMapper.updateMicroService(microServiceEntity);
    }

    @Override
    public void deleteMicroService(String id) {
        microServiceMapper.deleteMicroService(id);
    }

    @Override
    public void deleteMicroServiceByIds(String ids) {
        microServiceMapper.deleteMicroServiceByIds(ids.split("_"));
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
    public void synchronizeMicroService() {
        List<MicroServiceApplicationDTO> applicationList = getApplicationList();
        if (applicationList != null) {
            List<MicroServiceEntity> microServiceEntityList = new ArrayList<>();
            for (MicroServiceApplicationDTO microServiceApplicationDTO : applicationList) {
                List<ApplicationInstance> instanceList = microServiceApplicationDTO.getInstance();
                if (instanceList != null) {
                    for (ApplicationInstance instance : instanceList) {
                        MicroServiceEntity microServiceEntity = new MicroServiceEntity();
                        microServiceEntity.setId(PrimaryKeyUtil.generateId());
                        microServiceEntity.setMicroServiceName(microServiceApplicationDTO.getName());
                        microServiceEntity.setInstanceId(instance.getInstanceId());
                        microServiceEntity.setHostName(instance.getHostName());
                        microServiceEntity.setApp(instance.getApp());
                        microServiceEntity.setIpAddr(instance.getIpAddr());
                        microServiceEntityList.add(microServiceEntity);
                    }
                }
            }
            if (microServiceEntityList.size() > 0) {
                microServiceMapper.insertMicroServiceList(microServiceEntityList);
            }
        }
    }

    @Override
    public MicroServiceRepVO microServiceInfo(MicroServiceReqVO reqVO) {
        MicroServiceRepVO microServiceRep = new MicroServiceRepVO();
        List<MicroServiceEntity> list = microServiceMapper.microServiceMachineList(reqVO);
        Map<String, ApplicationInstance> microServiceMap = fetchMicroService();
        int normal = 0;
        for (MicroServiceEntity microServiceEntity : list) {
            microServiceRep.setMicroServiceName(microServiceEntity.getMicroServiceName());
            microServiceRep.setMicroServiceAlias(StringUtils.defaultValueIfEmpty(microServiceEntity.getMicroServiceAlias(),MonitorConstants.DEFAULT_DISPLAY_SYMBOL));
            ApplicationInstance applicationInstance = microServiceMap.get(microServiceEntity.getInstanceId());
            if (applicationInstance != null) {
                if (MonitorConstants.UP_STATUS.equals(applicationInstance.getStatus())) {
                    normal++;
                }
            }
        }
        if (normal == 0) {
            microServiceRep.setState("0");
        } else if (normal < list.size()) {
            microServiceRep.setState("1");
        } else if (normal == list.size()) {
            microServiceRep.setState("3");
        }
        return microServiceRep;
    }

    @Override
    public MicroServiceJvmMemoryVO jvmMemory(MicroServiceReqVO reqVO) {
        MicroServiceJvmMemoryVO microServiceJvmMemory = new MicroServiceJvmMemoryVO();
        List<MicroServiceEntity> list = microServiceMapper.microServiceMachineList(reqVO);

        Random random = new Random(System.currentTimeMillis());
        int hour = Integer.parseInt(DateUtils.getCurrentHour());

        List<String> xAxisData = new ArrayList<>();
        String suffix = ":00";
        for (int i = 0; i <= hour + 1; i++) {
            if (i < 10) {
                xAxisData.add("0" + i + suffix);
            } else {
                xAxisData.add(i + suffix);
            }
        }
        microServiceJvmMemory.setXAxisData(xAxisData);
        for (int i = 0; i < list.size(); i++) {
            microServiceJvmMemory.getLegendData().add(list.get(i).getIpAddr());
            microServiceJvmMemory.getColorData().add(LineColorEnum.values()[i].getColor());
            List<Integer> seriesData = new ArrayList<>();
            for (int j = 0; j <= hour + 1; j++) {
                seriesData.add(random.nextInt(1000));
            }
            microServiceJvmMemory.getSeriesData().add(seriesData);
        }
        return microServiceJvmMemory;
    }


    public Map<String, Object> getMicroServiceList3() {
        Random random = new Random(System.currentTimeMillis());
        List<MicroServiceStateVO> microServiceStatusList = new ArrayList<>();
        int count = 70 + random.nextInt(10);
        for (int i = 0; i < count; i++) {
            MicroServiceStateVO microServiceStatus = new MicroServiceStateVO();
            microServiceStatus.setId(String.valueOf(i));
            microServiceStatus.setMicroServiceName("订单服务" + i);
            microServiceStatus.setWarn(random.nextInt(500));
            microServiceStatus.setError(random.nextInt(5000));
            microServiceStatus.setState(String.valueOf(random.nextInt(3) + 1));
            microServiceStatusList.add(microServiceStatus);
        }
        Map<String, Object> retList = new HashMap<>();
        retList.put("list", microServiceStatusList);
        return retList;
    }


}
