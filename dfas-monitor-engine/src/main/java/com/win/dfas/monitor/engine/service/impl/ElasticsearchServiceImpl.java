package com.win.dfas.monitor.engine.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.win.dfas.monitor.common.dto.BucketDTO;
import com.win.dfas.monitor.config.elasticsearch.ESClient;
import com.win.dfas.monitor.engine.service.ElasticsearchService;

@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

    static Logger logger = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);
	
    @Autowired
    private ESClient esClient;
    
	@Override
	public Map<String,Long> getLogTotalCount() throws Exception {
		// 1、创建search请求
        SearchRequest searchRequest = new SearchRequest("monitor");

        // 2、用SearchSourceBuilder来构造查询请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("index.date", "20191023"));
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
        SearchResponse searchResponse = esClient.getRestHighLevelClient().search(searchRequest,RequestOptions.DEFAULT);

        //4、处理响应
        //搜索结果状态信息
        RestStatus status = searchResponse.status();
        TimeValue took = searchResponse.getTook();
        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
        boolean timedOut = searchResponse.isTimedOut();

        //分片搜索情况
        int totalShards = searchResponse.getTotalShards();
        int successfulShards = searchResponse.getSuccessfulShards();
        int failedShards = searchResponse.getFailedShards();
        for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
            // failures should be handled here
        }

        //处理搜索命中文档结果
        SearchHits hits = searchResponse.getHits();

        TotalHits totalHits = hits.getTotalHits();
        float maxScore = hits.getMaxScore();

        SearchHit[] searchHits = hits.getHits();

        for (SearchHit hit : searchHits) {
            String index = hit.getIndex();
            String id = hit.getId();
            float score = hit.getScore();
            logger.info("index:" + index + "  id:" + id);
        }

        Aggregations aggregations = searchResponse.getAggregations();
        Terms logCountAggregation = aggregations.get("log_count");
        Map<String,Long> bucketMap = new HashMap<>();
        for (Terms.Bucket buck : logCountAggregation.getBuckets()) {
            logger.info("key: " + buck.getKeyAsString());
            logger.info("docCount: " + buck.getDocCount());
            bucketMap.put(buck.getKeyAsString(), buck.getDocCount());
        }
		return bucketMap;
	}

}
