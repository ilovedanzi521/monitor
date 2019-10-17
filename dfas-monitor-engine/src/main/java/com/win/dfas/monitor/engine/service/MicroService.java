package com.win.dfas.monitor.engine.service;

import com.github.pagehelper.PageInfo;
import com.win.dfas.monitor.common.vo.MicroServiceMachineRepVO;
import com.win.dfas.monitor.common.vo.MicroServiceRepVO;
import com.win.dfas.monitor.common.vo.MicroServiceReqVO;

import java.util.List;

public interface MicroService {


    PageInfo<MicroServiceRepVO> getMicroServiceList(MicroServiceReqVO reqVO);

    List<MicroServiceRepVO> searchMicroService(MicroServiceReqVO microServiceReqVO);

    List<MicroServiceMachineRepVO> microServiceMachineList(MicroServiceReqVO microServiceReqVO);

    void insertMicroService(MicroServiceReqVO microServiceReqVO);

    void updateMicroService(MicroServiceReqVO microServiceReqVO);

    void deleteMicroService(String id);

    void deleteMicroServiceByIds(String ids);

    void synchronizeMicroService();

    MicroServiceRepVO microServiceInfo(MicroServiceReqVO reqVO);

}
