package com.liberty.solr.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.liberty.solr.SearchBean;
import com.liberty.solr.SolrConfig;

@Service
public class SearchService {

	private Logger logger = LoggerFactory.getLogger(SearchService.class);

	@Autowired
	@Qualifier("httpSolrServer")
	private SolrConfig solrConfig;

	public void addBean(SearchBean searchBean) {

		SolrClient solrClient = this.obtainSolrClient();

		try {
			solrClient.addBean(searchBean);
			UpdateResponse response = solrClient.commit();
			System.out.println(response.getElapsedTime());
		} catch (IOException | SolrServerException e) {
			logger.error("向solr写入数据出错");
			e.printStackTrace();
		}
	}

	public void removeBean(String query) {

		SolrClient solrClient = this.obtainSolrClient();

		try {
			solrClient.deleteByQuery(query);
			solrClient.commit();
		} catch (IOException | SolrServerException e) {
			logger.error("删除solr数据出错");
			e.printStackTrace();
		}
	}

	public List<SearchBean> obtainBeans(SolrParams query) {

		SolrClient solrClient = this.obtainSolrClient();
		List<SearchBean> searchBeanList = new ArrayList<>();
				
		try {
			QueryResponse response = solrClient.query(query, METHOD.POST);
			SolrDocumentList solrDocumentList = response.getResults();
			
			for(SolrDocument solrDocument : solrDocumentList){
				
				DocumentObjectBinder binder = new DocumentObjectBinder();
				SearchBean searchRltElem = binder.getBean(SearchBean.class, solrDocument);
				searchBeanList.add(searchRltElem);
			}
		} catch (IOException | SolrServerException e) {
			logger.error("查询solr数据出错");
			e.printStackTrace();
		}
		
		return searchBeanList;
	}
	
	private SolrClient obtainSolrClient() {
		SolrClient solrClient = null;
		try {
			solrClient = solrConfig.solrServer();
		} catch (Exception e) {
			logger.error("获取SolrClient出错");
			e.printStackTrace();
		}

		return solrClient;
	}
}
