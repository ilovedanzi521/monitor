package com.win.dfas.monitor.common.util;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 包名称：com.win.dfas.monitor.common.util
 * 类名称：SimpleRestClient
 * 类描述：RestTemplate同步请求工具类
 * 创建人：@author wangyaoheng
 * 创建时间：2019年8月7日/下午1:31:49
 */
public final class SimpleRestClient {

    private static class SingletonHolder {
        public final static SimpleRestClient instance = new SimpleRestClient();
    }

    public static SimpleRestClient getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 设置restTemplate对象
     */
    public RestTemplate initRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(0B111100 * 0B111100 * 1000);
        requestFactory.setConnectTimeout(30 * 1000);

        // 添加转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());

        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(new ResourceHttpMessageConverter());
        messageConverters.add(new SourceHttpMessageConverter<>());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        //RestTemplate上传文件出现中文乱码处理
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (Iterator<HttpMessageConverter<?>> iterator = list.iterator(); iterator.hasNext(); ) {
            HttpMessageConverter item = iterator.next();
            if (item instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) item).setDefaultCharset(StandardCharsets.UTF_8);
            } else if (item instanceof FormHttpMessageConverter) {
                ((FormHttpMessageConverter) item).setCharset(StandardCharsets.UTF_8);
                ((FormHttpMessageConverter) item).setMultipartCharset(StandardCharsets.UTF_8);
            }
        }
        return restTemplate;
    }

}