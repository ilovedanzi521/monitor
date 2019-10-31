package com.win.dfas.monitor.exporter.microservice.metrics;

import com.win.dfas.monitor.common.util.StringUtils;
import com.win.dfas.monitor.exporter.microservice.counter.HttpRequestTotalCounter;
import com.win.dfas.monitor.exporter.microservice.pushgateway.MonitorPushGateway;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Import({HttpRequestTotalCounter.class, MonitorPushGateway.class})
public class MonitorMetricsInterceptor {

    @Autowired
    private HttpRequestTotalCounter httpRequestTotalCounter;

    // 自定义Prometheus注解的全路径
/*    @Pointcut("@annotation(com.win.dfas.monitor.exporter.microservice.metrics.MonitorMetrics)")
    public void monitorMetricsMethod() {
    }*/

    @Pointcut("@annotation(com.win.dfas.monitor.exporter.microservice.metrics.MonitorMetrics)")
    public void monitorMetricsMethod() {
    }

    @Around(value = "monitorMetricsMethod() && @annotation(annotation)")
    public Object MetricsCollector(ProceedingJoinPoint joinPoint, MonitorMetrics annotation) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MonitorMetrics monitorMetrics = methodSignature.getMethod().getAnnotation(MonitorMetrics.class);
        if (monitorMetrics != null) {
            String metricsName = monitorMetrics.name();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (StringUtils.isEmpty(metricsName)) {
                metricsName = request.getRequestURI();
            }
            //String methodName = request.getMethod();

            //requestTotal.labels(name).inc();
            //AbstractMonitorCenter.inc();
            httpRequestTotalCounter.inc();
            //AbstractMonitorCenter.instance().open(metricsName);
            //Histogram.Timer requestTimer = histogram.labels(name).startTimer();
            Object object;
            try {
                object = joinPoint.proceed();
            } catch (Exception e) {
                //requestError.labels(name).inc();
                throw e;
            } finally {
                //requestTimer.observeDuration();
            }
            return object;
        } else {
            return joinPoint.proceed();
        }
    }
}
