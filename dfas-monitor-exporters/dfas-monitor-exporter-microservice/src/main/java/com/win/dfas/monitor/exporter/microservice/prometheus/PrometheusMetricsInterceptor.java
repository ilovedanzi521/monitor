package com.win.dfas.monitor.exporter.microservice.prometheus;

import io.prometheus.client.Counter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrometheusMetricsInterceptor extends HandlerInterceptorAdapter {

    static final Counter requestCounter = Counter.build()
            .name("io_namespace_http_requests_total").labelNames("path", "method", "code") //metric name建议使用_total结尾
            .help("Total requests.").register();

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        int status = response.getStatus();

        requestCounter.labels(requestURI, method, String.valueOf(status)).inc(); //调用inc()函数，每次请求发生时计数+1
        super.afterCompletion(request, response, handler, ex);
    }
}
