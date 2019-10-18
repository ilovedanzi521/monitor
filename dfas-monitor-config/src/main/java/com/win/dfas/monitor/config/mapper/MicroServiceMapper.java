package com.win.dfas.monitor.config.mapper;

import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.vo.MicroServiceReqVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroServiceMapper {

    List<MicroServiceEntity> selectMicroServiceList(MicroServiceEntity microServiceEntity);

    void insertMicroService(MicroServiceEntity microServiceEntity);

    void updateMicroService(MicroServiceEntity microServiceEntity);

    void clearMicroService();

    void deleteMicroService(@Param(value="id") Long id);

    MicroServiceEntity selectMicroService(@Param(value="id") Long id);

    void deleteMicroServiceByIds(@Param(value="ids") List ids);

    int insertMicroServiceList(List<MicroServiceEntity> microServiceList);

}
