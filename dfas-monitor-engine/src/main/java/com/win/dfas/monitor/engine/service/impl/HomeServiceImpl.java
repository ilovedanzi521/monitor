package com.win.dfas.monitor.engine.service.impl;

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
import com.win.dfas.monitor.config.mapper.MicroServiceInstanceMapper;
import com.win.dfas.monitor.config.mapper.MicroServiceMapper;
import com.win.dfas.monitor.engine.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class HomeServiceImpl implements HomeService {

    static Logger log = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    private EurekaService eurekaService;

    @Autowired
    private IDcDevcieService dcDevcieService;

    @Autowired
    private MicroServiceMapper microServiceMapper;

    @Autowired
    private MicroServiceInstanceMapper microServiceInstanceMapper;

    @Autowired
    private PrometheusService prometheusService;

    @Autowired
    private ElasticsearchService elasticsearchService;


    /** 千分位格式化 */
    protected NumberFormat thousandBitNumberFormat = NumberFormat.getNumberInstance();

    @Override
    public PlatformOverviewVO getPlatformOverviewData() {
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(thousandBitNumberFormat.format(prometheusService.getQps())));
        platformOverview.setTotalHttpRequest(String.valueOf(thousandBitNumberFormat.format(prometheusService.getHttpRequestTotal())));
        platformOverview.setTotalMicroService(String.valueOf(thousandBitNumberFormat.format(microServiceMapper.getTotalMicroService())));
        platformOverview.setTotalNode(String.valueOf(thousandBitNumberFormat.format(dcDevcieService.getTotalNode())));
        return platformOverview;
    }

    @Override
    public String getQpsData() {
        return prometheusService.getQpsChart();
    }

    @Override
    public List<MicroServiceRepVO> getMicroServiceStateData() {

        List<MicroServiceEntity> microServiceEntityList = microServiceMapper.selectMicroServiceList(null);
        List<MicroServiceRepVO> microServiceRepList = ObjectUtils.copyPropertiesList(microServiceEntityList, MicroServiceRepVO.class);
        Map<String, ApplicationInstance> microServiceInstanceMap = eurekaService.fetchMicroService();
        for (MicroServiceRepVO microServiceRepVO : microServiceRepList) {
            List<MicroServiceInstanceEntity> instanceList = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceRepVO.getId());
            int upCount = 0;
            if (instanceList != null) {
                for (MicroServiceInstanceEntity microServiceInstanceEntity : instanceList) {
                    ApplicationInstance applicationInstance = microServiceInstanceMap.get(microServiceInstanceEntity.getIpAddr());
                    if (applicationInstance != null) {
                        if (MonitorConstants.UP_STATUS.equals(applicationInstance.getStatus())) {
                            upCount++;
                        }
                    }
                }
            }

            if (instanceList == null || upCount == 0) {
                microServiceRepVO.setState(StatusEnum.OFFLINE.getStatus());
            } else if (upCount < instanceList.size()) {
                microServiceRepVO.setState(StatusEnum.EXCEPTION.getStatus());
            } else if (upCount == instanceList.size()) {
                microServiceRepVO.setState(StatusEnum.ONLINE.getStatus());
                //继续判断JVM内存是否存在告警，存在，则设置为告警状态
            }

            try {
                Map<String, Long> bucketMap = elasticsearchService.getLogTotalCountByMicroService(microServiceRepVO.getMicroServiceName());
                long warnCount = 0;
                if (bucketMap.get(LogLevelEnum.WARN.name()) != null) {
                    warnCount = bucketMap.get(LogLevelEnum.WARN.name());
                }
                long errorCount = 0;
                if (bucketMap.get(LogLevelEnum.ERROR.name()) != null) {
                    errorCount = bucketMap.get(LogLevelEnum.ERROR.name());
                }
                microServiceRepVO.setWarn(String.valueOf(thousandBitNumberFormat.format(warnCount)));
                microServiceRepVO.setError(String.valueOf(thousandBitNumberFormat.format(errorCount)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return microServiceRepList;
    }

    @Override
    public ExceptionVO getExceptionData() {
        ExceptionVO exception = new ExceptionVO();
        try {
            Map<String, Long> bucketMap = elasticsearchService.getLogTotalCount();
            Random random = new Random(System.currentTimeMillis());
            exception.setMachineCpu(String.valueOf(thousandBitNumberFormat.format(random.nextInt(100))));
            exception.setMachineMemory(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10000))));
            exception.setMachineDisk(String.valueOf(thousandBitNumberFormat.format(random.nextInt(1000))));
            exception.setMicroServiceMemory(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10))));
            long warnCount = 0;
            if (bucketMap.get("WARN") != null) {
                warnCount = bucketMap.get("WARN");
            }
            long errorCount = 0;
            if (bucketMap.get("ERROR") != null) {
                errorCount = bucketMap.get("ERROR");
            }
            exception.setMicroServiceWarnLog(String.valueOf(thousandBitNumberFormat.format(warnCount)));
            exception.setMicroServiceErrorLog(String.valueOf(thousandBitNumberFormat.format(errorCount)));

        } catch (Exception e) {
        }

        return exception;
    }
}