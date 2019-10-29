package com.win.dfas.monitor.engine.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.engine.service.ElasticsearchService;

@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

    static Logger logger = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Value("${spring.elasticsearch.index}")
    private String elasticsearchIndex;


    @Override
    public Map<String, Long> getLogTotalCount() throws Exception {
        // 1、创建search请求
        SearchRequest searchRequest = new SearchRequest(elasticsearchIndex);

        // 2、用SearchSourceBuilder来构造查询请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("index.date", DateUtils.dateTime()));
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //加入聚合
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("log_count")
                .field("level.keyword");
        sourceBuilder.aggregation(aggregation);

        //将请求体加入到请求中
        searchRequest.source(sourceBuilder);


        //3、发送请求
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //4、处理响应
        //搜索结果状态信息
        Aggregations aggregations = searchResponse.getAggregations();
        Terms logCountAggregation = aggregations.get("log_count");
        Map<String, Long> bucketMap = new HashMap<>();
        for (Terms.Bucket buck : logCountAggregation.getBuckets()) {
            logger.info("key: " + buck.getKeyAsString());
            logger.info("docCount: " + buck.getDocCount());
            bucketMap.put(buck.getKeyAsString(), buck.getDocCount());
        }
        return bucketMap;
    }

    @Override
    public Map<String, Long> getLogTotalCountByMicroService(String microServiceName) throws Exception {
        // 1、创建search请求
        SearchRequest searchRequest = new SearchRequest(elasticsearchIndex);

        // 2、用SearchSourceBuilder来构造查询请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.boolQuery()
                .must(
                        QueryBuilders.matchPhraseQuery("index.date", DateUtils.dateTime())
                ).must(
                        QueryBuilders.matchPhraseQuery("springAppName", microServiceName.toLowerCase()))
        );
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //加入聚合
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("log_count")
                .field("level.keyword");
        sourceBuilder.aggregation(aggregation);

        //将请求体加入到请求中
        searchRequest.source(sourceBuilder);


        //3、发送请求
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //4、处理响应
        //搜索结果状态信息
        Aggregations aggregations = searchResponse.getAggregations();
        Terms logCountAggregation = aggregations.get("log_count");
        Map<String, Long> bucketMap = new HashMap<>();
        for (Terms.Bucket buck : logCountAggregation.getBuckets()) {
            logger.info("key: " + buck.getKeyAsString());
            logger.info("docCount: " + buck.getDocCount());
            bucketMap.put(buck.getKeyAsString(), buck.getDocCount());
        }
        return bucketMap;
    }

    @Override
    public Map<String, Long> getLogTotalCountByMicroService(String microServiceName, String ip, String port) throws Exception {
        // 1、创建search请求
        SearchRequest searchRequest = new SearchRequest(elasticsearchIndex);

        // 2、用SearchSourceBuilder来构造查询请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.boolQuery()
                .must(
                        QueryBuilders.matchPhraseQuery("index.date", DateUtils.dateTime())
                ).must(
                        QueryBuilders.matchPhraseQuery("springAppName", microServiceName.toLowerCase())
                ).must(
                        QueryBuilders.matchPhraseQuery("ip", ip)
                )/*.must(
                        QueryBuilders.matchPhraseQuery("port", port)
                )*/);
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //加入聚合
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("log_count")
                .field("level.keyword");
        sourceBuilder.aggregation(aggregation);

        //将请求体加入到请求中
        searchRequest.source(sourceBuilder);


        //3、发送请求
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //4、处理响应
        //搜索结果状态信息
        Aggregations aggregations = searchResponse.getAggregations();
        Terms logCountAggregation = aggregations.get("log_count");
        Map<String, Long> bucketMap = new HashMap<>();
        for (Terms.Bucket buck : logCountAggregation.getBuckets()) {
            logger.info("key: " + buck.getKeyAsString());
            logger.info("docCount: " + buck.getDocCount());
            bucketMap.put(buck.getKeyAsString(), buck.getDocCount());
        }
        return bucketMap;
    }


}
