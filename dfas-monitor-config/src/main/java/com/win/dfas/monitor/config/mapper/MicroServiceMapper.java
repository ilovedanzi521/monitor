package com.win.dfas.monitor.config.mapper;

import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.vo.MicroServiceReqVO;

import java.util.List;

public interface MicroServiceMapper {

    List<MicroServiceEntity> selectMicroServiceList(MicroServiceReqVO reqVO);

    void insertMicroService(MicroServiceEntity microServiceEntity);

    void updateMicroService(MicroServiceEntity microServiceEntity);

    void deleteMicroService(String id);

    List<MicroServiceEntity> searchMicroService(MicroServiceReqVO reqVO);

    void deleteMicroServiceByIds(String[] ids);

}
