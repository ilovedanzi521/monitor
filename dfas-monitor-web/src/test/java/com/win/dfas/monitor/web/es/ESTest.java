package com.win.dfas.monitor.web.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class ESTest {

    static Logger logger = LoggerFactory.getLogger(ESTest.class);

	/*@Autowired
	private ESClient esClient;
	
	@Test
	public void test() throws Exception {
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
	    SearchResponse searchResponse = esClient.getRestHighLevelClient().search(searchRequest, null);
	
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
	    for (Terms.Bucket buck : logCountAggregation.getBuckets()) {
	        logger.info("key: " + buck.getKeyAsString());
	        logger.info("docCount: " + buck.getDocCount());
	    }
	}*/
}
