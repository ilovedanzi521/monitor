package com.win.dfas.monitor.engine.service;

import com.github.pagehelper.PageInfo;
import com.win.dfas.monitor.common.vo.MicroServiceRepVO;
import com.win.dfas.monitor.common.vo.MicroServiceReqVO;

public interface MonitorService {

    String getQps();

    String getHttpRequestTotal();

    PageInfo<MicroServiceRepVO> getMicroServiceList(MicroServiceReqVO reqVO);

}
