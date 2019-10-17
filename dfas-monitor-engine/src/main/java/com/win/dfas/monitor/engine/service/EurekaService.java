package com.win.dfas.monitor.engine.service;

import com.win.dfas.monitor.common.dto.MicroServiceApplicationDTO;
import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;

import java.util.List;
import java.util.Map;

public interface EurekaService {

    List<MicroServiceApplicationDTO> getApplicationList();

    Map<String, ApplicationInstance> fetchMicroService();

}
