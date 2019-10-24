package com.win.dfas.monitor.engine.service;

import java.util.Map;

public interface ElasticsearchService {

	Map<String,Long> getLogTotalCount() throws Exception ;

	Map<String,Long> getLogTotalCountByMicroService(String microServiceName) throws Exception ;
	
}
