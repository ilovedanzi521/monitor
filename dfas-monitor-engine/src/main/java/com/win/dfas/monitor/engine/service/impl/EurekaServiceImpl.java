package com.win.dfas.monitor.engine.service.impl;

import com.win.dfas.monitor.common.dto.MicroServiceApplicationDTO;
import com.win.dfas.monitor.common.dto.MicroServiceDTO;
import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.engine.service.EurekaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EurekaServiceImpl implements EurekaService {

    @Value("${registration.center.url}")
    private String registrationCenterUrl;

    @Override
    public List<MicroServiceApplicationDTO> getApplicationList() {
        String url = registrationCenterUrl + "/eureka/apps";
        String result = RestfulTools.get(url, String.class);
        MicroServiceDTO microService = JsonUtil.toObject(result, MicroServiceDTO.class);
        return microService.getApplications().getApplication();
    }

    @Override
    public Map<String, ApplicationInstance> fetchMicroService() {
        Map<String, ApplicationInstance> microServiceMap = new HashMap<>();
        List<MicroServiceApplicationDTO> applicationList = getApplicationList();
        if (applicationList != null) {
            for (MicroServiceApplicationDTO microServiceApplicationDTO : applicationList) {
                List<ApplicationInstance> instanceList = microServiceApplicationDTO.getInstance();
                if (instanceList != null) {
                    for (ApplicationInstance instance : instanceList) {
                        microServiceMap.put(instance.getIpAddr(), instance);
                    }
                }
            }
        }
        return microServiceMap;
    }


}
