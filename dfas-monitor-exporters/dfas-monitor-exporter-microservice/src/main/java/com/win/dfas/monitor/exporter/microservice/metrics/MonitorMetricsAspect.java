package com.win.dfas.monitor.exporter.microservice.metrics;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.win.dfas.monitor.common.util.StringUtils;
import com.win.dfas.monitor.exporter.microservice.center.AbstractMonitorCenter;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
public class MonitorMetricsAspect {

	private static final String POINT_CUT_CLASS_NAME = "com.win.dfas.monitor.exporter.microservice.metrics.MonitorMetrics";

	/*    private static final Counter requestTotal = Counter.build().name("couter_all").labelNames("api").help
	        ("total request couter of api").register();
	private static final Counter requestError = Counter.build().name("couter_error").labelNames("api").help
	        ("response Error couter of api").register();
	private static final Histogram histogram = Histogram.build().name("histogram_consuming").labelNames("api").help
	        ("response consuming of api").register();*/

	// 自定义Prometheus注解的全路径
	@Pointcut("@annotation(" + POINT_CUT_CLASS_NAME + ")")
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
			AbstractMonitorCenter.instance().open(metricsName);
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
