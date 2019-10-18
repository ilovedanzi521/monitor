package com.win.dfas.monitor.engine.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.win.dfas.common.util.ObjectUtils;
import com.win.dfas.common.util.PrimaryKeyUtil;
import com.win.dfas.monitor.common.constant.LineColorEnum;
import com.win.dfas.monitor.common.constant.MonitorConstants;
import com.win.dfas.monitor.common.constant.StatusEnum;
import com.win.dfas.monitor.common.dto.MicroServiceApplicationDTO;
import com.win.dfas.monitor.common.dto.MicroServiceDTO;
import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;
import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.entity.MicroServiceInstanceEntity;
import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.util.StringUtils;
import com.win.dfas.monitor.common.vo.*;
import com.win.dfas.monitor.config.mapper.MicroServiceInstanceMapper;
import com.win.dfas.monitor.config.mapper.MicroServiceMapper;
import com.win.dfas.monitor.engine.service.MicroService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MicroServiceImpl implements MicroService {

    @Autowired
    private MicroServiceMapper microServiceMapper;

    @Autowired
    private MicroServiceInstanceMapper microServiceInstanceMapper;

    @Value("${prometheus.server.url}")
    private String prometheusServerUrl;

    @Value("${registration.center.url}")
    private String registrationCenterUrl;


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
            List<MicroServiceInstanceEntity> microServiceInstanceList = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceRepVO.getId());
            StringBuilder sb = new StringBuilder();
            int size = microServiceInstanceList.size();
            for (int i = 0; i < size; i++) {
                MicroServiceInstanceEntity microServiceInstanceEntity = microServiceInstanceList.get(i);
                if (i == (size - 1)) {
                    sb.append(microServiceInstanceEntity.getIpAddr());
                } else {
                    sb.append(microServiceInstanceEntity.getIpAddr() + ",");
                }
            }
            microServiceRepVO.setIpAddress(sb.toString());
        }
        return pageList;
    }

    @Override
    public List<MicroServiceRepVO> searchMicroService(MicroServiceReqVO microServiceReqVO) {
        MicroServiceEntity microServiceEntity =new MicroServiceEntity();
        BeanUtils.copyProperties(microServiceReqVO,microServiceEntity);
        List<MicroServiceEntity> list = microServiceMapper.selectMicroServiceList(microServiceEntity);
        return ObjectUtils.copyPropertiesList(list, MicroServiceRepVO.class);
    }

    @Override
    public List<MicroServiceMachineRepVO> microServiceMachineList(MicroServiceReqVO microServiceReqVO) {
        List<MicroServiceInstanceEntity> list =microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceReqVO.getId());
        List<MicroServiceMachineRepVO> microServiceMachineRepList = ObjectUtils.copyPropertiesList(list, MicroServiceMachineRepVO.class);
        Map<String, ApplicationInstance> microServiceMap = fetchMicroService();
        for (MicroServiceMachineRepVO microServiceMachineRep : microServiceMachineRepList) {
            //设置机器状态、JVM内存

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
            String ipAddrs[] = microServiceReqVO.getIpAddress().split(",");
            for (String ipAddr : ipAddrs) {
                MicroServiceInstanceEntity microServiceInstanceEntity = new MicroServiceInstanceEntity();
                microServiceInstanceEntity.setId(PrimaryKeyUtil.generateId());
                microServiceInstanceEntity.setServiceId(microServiceEntity.getId());
                microServiceInstanceEntity.setIpAddr(ipAddr);
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
            String ipAddresses[] = microServiceReqVO.getIpAddress().split(",");
            List<String> ipAddressList = new ArrayList<>(Arrays.asList(ipAddresses));
            List<MicroServiceInstanceEntity> instanceList = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceEntity.getId());
            List<String> existList=new ArrayList<>();
            if (instanceList != null) {
                for (MicroServiceInstanceEntity microServiceInstanceEntity : instanceList) {
                    if (!ipAddressList.contains(microServiceInstanceEntity.getIpAddr())){
                        microServiceInstanceMapper.deleteMicroServiceInstance(microServiceInstanceEntity.getId());
                    }else{
                        existList.add(microServiceInstanceEntity.getIpAddr());
                    }
                }
            }
            for(String addr:ipAddressList){
                if(!existList.contains(addr)){
                    MicroServiceInstanceEntity microServiceInstanceEntity = new MicroServiceInstanceEntity();
                    microServiceInstanceEntity.setId(PrimaryKeyUtil.generateId());
                    microServiceInstanceEntity.setServiceId(microServiceEntity.getId());
                    microServiceInstanceEntity.setApp(microServiceEntity.getMicroServiceName());
                    microServiceInstanceEntity.setIpAddr(addr);
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
        String strIds[]=ids.split("_");
        List<Long> longIds=new ArrayList<>();
        for(int i=0;i<strIds.length;i++){
            longIds.add(Long.parseLong(strIds[i]));
        }
        microServiceMapper.deleteMicroServiceByIds(longIds);
        for(int i=0;i<longIds.size();i++){
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
        MicroServiceRepVO microServiceRep = new MicroServiceRepVO();
        MicroServiceEntity microServiceEntity = microServiceMapper.selectMicroService(reqVO.getId());
        MicroServiceRepVO microServiceRepVO = new MicroServiceRepVO();
        BeanUtils.copyProperties(microServiceEntity,microServiceRepVO);
        Map<String, ApplicationInstance> microServiceInstanceMap = fetchMicroService();
        List<MicroServiceInstanceEntity> instanceList = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceRepVO.getId());
        int upCount=0;
        if(instanceList != null){
            for(MicroServiceInstanceEntity microServiceInstanceEntity:instanceList){
                ApplicationInstance applicationInstance = microServiceInstanceMap.get(microServiceInstanceEntity.getIpAddr());
                if(applicationInstance != null){
                    if(MonitorConstants.UP_STATUS.equals(applicationInstance.getStatus())){
                        upCount++;
                    }
                }
            }
        }
        if(instanceList == null  ||  upCount ==0) {
            microServiceRepVO.setState(StatusEnum.OFFLINE.getStatus());
        }else if(upCount  < instanceList.size()){
            microServiceRepVO.setState(StatusEnum.EXCEPTION.getStatus());
        }else if(upCount ==instanceList.size()){
            microServiceRepVO.setState(StatusEnum.ONLINE.getStatus());
            //继续判断JVM内存是否存在告警，存在，则设置为告警状态
        }
        return microServiceRep;
    }

    @Override
    public MicroServiceJvmMemoryVO jvmMemory(MicroServiceReqVO reqVO) {
        MicroServiceJvmMemoryVO microServiceJvmMemory = new MicroServiceJvmMemoryVO();
        List<MicroServiceInstanceEntity> microServiceInstanceEntityList = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(reqVO.getId());

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
        for (int i = 0; i < microServiceInstanceEntityList.size(); i++) {
            MicroServiceInstanceEntity microServiceInstanceEntity=microServiceInstanceEntityList.get(i);
            microServiceJvmMemory.getLegendData().add(microServiceInstanceEntity.getIpAddr());
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
