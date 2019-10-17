package com.win.dfas.monitor.config.mapper;

import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.vo.MicroServiceMachineRepVO;
import com.win.dfas.monitor.common.vo.MicroServiceReqVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroServiceMapper {

    List<MicroServiceEntity> selectMicroServiceList(MicroServiceReqVO reqVO);

    void insertMicroService(MicroServiceEntity microServiceEntity);

    void updateMicroService(MicroServiceEntity microServiceEntity);

    void deleteMicroService(String id);

    List<MicroServiceEntity> searchMicroService(MicroServiceReqVO reqVO);

    void deleteMicroServiceByIds(String[] ids);

    int insertMicroServiceList(List<MicroServiceEntity> microServiceList);

    List<MicroServiceEntity> microServiceMachineList(MicroServiceReqVO microServiceReqVO);
}
