package com.win.dfas.monitor.engine.service;

import com.win.dfas.common.util.ObjectUtils;
import com.win.dfas.monitor.common.constant.LogLevelEnum;
import com.win.dfas.monitor.common.constant.MonitorConstants;
import com.win.dfas.monitor.common.constant.StatusEnum;
import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;
import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.entity.MicroServiceInstanceEntity;
import com.win.dfas.monitor.common.vo.ExceptionVO;
import com.win.dfas.monitor.common.vo.MicroServiceRepVO;
import com.win.dfas.monitor.common.vo.PlatformOverviewVO;
import com.win.dfas.monitor.common.vo.QpsVO;

import java.util.List;
import java.util.Map;
import java.util.Random;

public interface HomeService {

    public PlatformOverviewVO getPlatformOverviewData();

    public QpsVO getQpsData();

    public List<MicroServiceRepVO> getMicroServiceStateData() ;

    public ExceptionVO getExceptionData() ;

}
