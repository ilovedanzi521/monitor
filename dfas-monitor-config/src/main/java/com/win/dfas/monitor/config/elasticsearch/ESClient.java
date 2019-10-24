package com.win.dfas.monitor.config.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySources(value = { @PropertySource("classpath:es.properties") })
public class ESClient {

    private static final String CLUSTER_NODES_SPLIT_SYMBOL = ",";
    private static final String HOST_PORT_SPLIT_SYMBOL = ":";
    private static final String SCHEMA = "http";

    @Value("${spring.elasticsearch.rest.uris}")
    private String clusterNodes;


    @Bean
    public RestHighLevelClient getRestHighLevelClient() {
        String[] clusterNodeArray = clusterNodes.trim().split(CLUSTER_NODES_SPLIT_SYMBOL);
        int length = clusterNodeArray.length;
        HttpHost[] httpHostArray = new HttpHost[clusterNodeArray.length];
        for (int i = 0; i < length; i++) {
            String clusterNode = clusterNodeArray[i];
            String[] clusterNodeInfoArray = clusterNode.trim().split(HOST_PORT_SPLIT_SYMBOL);
            httpHostArray[i] = new HttpHost(clusterNodeInfoArray[0], Integer.parseInt(clusterNodeInfoArray[1]), SCHEMA);
        }

        return new RestHighLevelClient(RestClient.builder(httpHostArray));
    }

    /*
	@Bean
	public RestHighLevelClient getRestHighLevelClient() {
		String clusterNodes = environment.getRequiredProperty("elasticsearch.cluster-nodes");
		if (StringUtils.isEmpty(clusterNodes)) {
			throw new RuntimeException("elasticsearch.cluster-nodes is empty.");
		}
		String schema = environment.getRequiredProperty("elasticsearch.schema");
		if (StringUtils.isEmpty(schema)) {
			throw new RuntimeException("elasticsearch.schema is empty.");
		}
		String[] clusterNodeArray = clusterNodes.trim().split(CLUSTER_NODES_SPLIT_SYMBOL);
		int length = clusterNodeArray.length;
		HttpHost[] httpHostArray = new HttpHost[clusterNodeArray.length];
		for (int i=0;i<length;i++ ) {
			String clusterNode= clusterNodeArray[i];
			String[] clusterNodeInfoArray = clusterNode.trim().split(HOST_PORT_SPLIT_SYMBOL);
			httpHostArray[i]=new HttpHost(clusterNodeInfoArray[0], Integer.parseInt(clusterNodeInfoArray[1]), schema);
		}

		return new RestHighLevelClient(RestClient.builder(httpHostArray));
	}
    */

}
