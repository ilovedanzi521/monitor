package com.win.dfas.monitor.config.mapper;

import com.win.dfas.monitor.common.entity.MicroServiceInstanceEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroServiceInstanceMapper {

    List<MicroServiceInstanceEntity> selectMicroServiceInstanceList(MicroServiceInstanceEntity microServiceInstanceEntity);

    List<MicroServiceInstanceEntity> selectMicroServiceInstanceListByServiceId(@Param(value="serviceId") Long serviceId);

    void insertMicroServiceInstance(MicroServiceInstanceEntity microServiceInstanceEntity);

    void updateMicroServiceInstance(MicroServiceInstanceEntity microServiceInstanceEntity);

    void deleteMicroServiceInstance(@Param(value="id") Long id);

    void deleteMicroServiceInstanceByServiceId(@Param(value="serviceId") Long serviceId);

    void deleteMicroServiceInstanceByIds(@Param(value="ids") String[] ids);

    int insertMicroServiceInstanceList(List<MicroServiceInstanceEntity> microServiceList);

}
