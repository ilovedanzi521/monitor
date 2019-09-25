package com.win.dfas.monitor.common.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 包名称：com.win.dfas.monitor.common.util
 * 类名称：RestfulTools
 * 类描述：RestTemplate同步请求工具类
 * 创建人：@author wangyaoheng
 * 创建时间：2019年8月7日/下午1:31:49
 */
public class RestfulTools {

    private static RestTemplate restTemplate;

    static {
        restTemplate = SimpleRestClient.getInstance().initRestTemplate();
    }

    /**
     * Get方法
     *
     * @param url:地址
     * @param returnClassName:返回对象类型,如:String.class
     * @param parameters:parameter参数
     * @return
     */
    public static <T> T get(String url, Class<T> returnClassName, Map<String, Object> parameters) {
        if (parameters == null) {
            return restTemplate.getForObject(url, returnClassName);
        }
        return restTemplate.getForObject(url, returnClassName, parameters);
    }

    /**
     * Get方法
     *
     * @param url:地址
     * @param returnClassName:返回对象类型,如:String.class
     * @param url:parameter参数
     * @return
     */
    public static <T> T get(String url, Class<T> returnClassName) {
        return restTemplate.getForObject(url, returnClassName);
    }

    /**
     * post请求,包含了路径,返回类型,Header,Parameter
     *
     * @param                url:地址
     * @param                returnClassName:返回对象类型,如:String.class
     * @param inputHeader
     * @param uriParameter
     * @param jsonBody
     * @return
     */
    public static <T> T post(String url, Class<T> returnClassName, Map<String, String> inputHeader, Map<String, Object> uriParameter, Object jsonBody) {
        // 请求Header
        HttpHeaders httpHeaders = new HttpHeaders();
        // 拼接Header
        if (inputHeader != null) {
            for (Map.Entry<String, String> entry : inputHeader.entrySet()) {
                httpHeaders.add(entry.getKey(), inputHeader.get(entry.getKey()));
            }
        }
        // 设置请求的类型及编码
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        httpHeaders.setContentType(type);
        httpHeaders.add("Accept", "application/json");
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.ALL);
        httpHeaders.setAccept(acceptableMediaTypes);

        @SuppressWarnings({"unchecked", "rawtypes"})
        HttpEntity<?> formEntity = new HttpEntity(jsonBody, httpHeaders);
        if (uriParameter == null) {
            return restTemplate.postForObject(url, formEntity, returnClassName);
        }
        return restTemplate.postForObject(url, formEntity, returnClassName, uriParameter);
    }

    /**
     * post请求,包含了路径,返回类型,Header,Parameter
     *
     * @param                url:地址
     * @param                returnClassName:返回对象类型,如:String.class
     * @param uriParameter
     * @param requestParameter
     * @return
     */
    public static <T> T postFromByUrlEncode(String url, Class<T> returnClassName, Map<String, Object> uriParameter, Map<String, Object> requestParameter) {
        // 请求Header
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求的类型及编码
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        httpHeaders.setContentType(type);
        httpHeaders.add("Accept", "application/x-www-form-urlencoded");
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.ALL);
        httpHeaders.setAccept(acceptableMediaTypes);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        if (requestParameter != null) {
            for (Map.Entry<String, Object> entry : requestParameter.entrySet()) {
                params.add(entry.getKey(), StringUtils.defaultString(requestParameter.get(entry.getKey())));
            }
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        HttpEntity<?> formEntity = new HttpEntity(params, httpHeaders);
        if (uriParameter == null) {
            return restTemplate.postForObject(url, formEntity, returnClassName);
        }
        return restTemplate.postForObject(url, formEntity, returnClassName, uriParameter);
    }

    public static String postFromData(String url, Map<String, Object> requestParameter) {

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        if (requestParameter != null) {
            for (Map.Entry<String, Object> entry : requestParameter.entrySet()) {
                params.add(entry.getKey(), StringUtils.defaultString(requestParameter.get(entry.getKey())));
            }
        }
        // 请求Header
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求的类型及编码
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        httpHeaders.setContentType(type);
        httpHeaders.add("Accept", "multipart/form-data");
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.ALL);
        httpHeaders.setAccept(acceptableMediaTypes);

        @SuppressWarnings({"unchecked", "rawtypes"})
        HttpEntity<?> formEntity = new HttpEntity(params, httpHeaders);
        return restTemplate.postForObject(url, formEntity, String.class);
    }


    public static <T> T post(String url, Class<T> returnClassName, String jsonData) {
        return restTemplate.postForObject(url, null, returnClassName, jsonData);
    }

    public static String post(String url, Object jsonBody) {
        // 请求Header
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求的类型及编码
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        httpHeaders.setContentType(type);
        httpHeaders.add("Accept", "application/json");
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.ALL);
        httpHeaders.setAccept(acceptableMediaTypes);

        @SuppressWarnings({"unchecked", "rawtypes"})
        HttpEntity<?> httpEntity = new HttpEntity(jsonBody, httpHeaders);
        return restTemplate.postForObject(url, httpEntity, String.class);
    }

    public static void delete(String url, Object... urlVariables) {
        restTemplate.delete(url, urlVariables);
    }

    public static void delete(String url, Map<String, ?> urlVariables) {
        restTemplate.delete(url, urlVariables);
    }


    public static <T> T postFile(String url, Class<T> returnClassName, MultiValueMap<String, Object> params) {
        // 请求Header
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求的类型及编码
        MediaType type = MediaType.parseMediaType("multipart/form-data; charset=UTF-8");
        httpHeaders.setContentType(type);
        httpHeaders.add("Accept", "multipart/form-data");
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.ALL);
        httpHeaders.setAccept(acceptableMediaTypes);

        @SuppressWarnings({"unchecked", "rawtypes"})
        HttpEntity<?> formEntity = new HttpEntity(params, httpHeaders);
        return restTemplate.postForObject(url, formEntity, returnClassName);
    }


    public static String postFile(String url, Map<String, Object> requestParameter, List<File> files) {
        return postFile(url, requestParameter, "uploadFiles", files);
    }


    public static String postFile(String url, Map<String, Object> requestParameter, String fileKey, List<File> files) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        if (requestParameter != null) {
            for (Map.Entry<String, Object> entry : requestParameter.entrySet()) {
                params.add(entry.getKey(), StringUtils.defaultString(requestParameter.get(entry.getKey())));
            }
        }
        for (File file : files) {
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            params.add(fileKey, fileSystemResource);
        }
        return postFile(url, String.class, params);
    }

    public static String postFile(String url, Map<String, Object> requestParameter, String fileKey, MultipartFile[] uploadFiles) throws Exception {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        if (requestParameter != null) {
            for (Map.Entry<String, Object> entry : requestParameter.entrySet()) {
                params.add(entry.getKey(), StringUtils.defaultString(requestParameter.get(entry.getKey())));
            }
        }
        for (MultipartFile uploadFile : uploadFiles) {
            ByteArrayResource byteArrayResource = new ByteArrayResource(uploadFile.getBytes()) {
                @Override
                public String getFilename() throws IllegalStateException {
                    return uploadFile.getOriginalFilename();
                }
            };
            params.add(fileKey, byteArrayResource);
        }
        return postFile(url, String.class, params);
    }

    public static String postFile(String url, Map<String, Object> requestParameter, String fileKey, File file) {
        List<File> files = new ArrayList<File>();
        files.add(file);
        return postFile(url, requestParameter, fileKey, files);
    }

    public static byte[] getFile(String url) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<String>(headers), byte[].class);
        return response.getBody();
    }

    public static byte[] getFile(String url, String jsonBody) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<String>(jsonBody, headers), byte[].class);
        return response.getBody();
    }

    /**
     * 发送/获取 服务端数据(主要用于解决发送put,delete方法无返回值问题).
     *
     * @param url      绝对地址
     * @param method   请求方式
     * @param bodyType 返回类型
     * @param          <T> 返回类型
     * @return 返回结果(响应体)
     */
    public static <T> T exchange(String url, HttpMethod method, Class<T> bodyType, Object value) {

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        // 请求体
        headers.setContentType(mediaType);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        String requestJson = null;
        try {
            requestJson = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 发送请求
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> resultEntity = restTemplate.exchange(url, method, entity, bodyType);
        return resultEntity.getBody();
    }
}
