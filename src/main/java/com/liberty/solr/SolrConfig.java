package com.liberty.solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

//@Configuration
//@EnableSolrRepositories(basePackages = { "com.liberty.solr" }, multicoreSupport = true)
public class SolrConfig {

	private String zkHost = "192.168.161.250:2181,192.168.161.174:2181,192.168.161.195:2181";

	private String solrCloud = "http://192.168.161.250:8181/solr,http://192.168.161.250:8080/solr,http://192.168.161.250:8180/solr";

	private String host = "http://192.168.161.174:8983/solr/collection1";
	
	public SolrConfig() {
	}

	public SolrConfig(String zkHost, String solrCloud) {

		this.zkHost = zkHost;
		this.solrCloud = solrCloud;
	}

	// @Bean
	public SolrClient solrServer() throws MalformedURLException {

		String sorlServer[] = this.solrCloud.split(",");
		LBHttpSolrClient lbHttpSolrClient = new LBHttpSolrClient(sorlServer);
		CloudSolrClient solrClient = new CloudSolrClient(this.zkHost,
				lbHttpSolrClient);

		solrClient.setDefaultCollection("gettingstarted");

		return solrClient;
	}

	public SolrClient httpSolrServer() {
		SolrClient solrClient = new HttpSolrClient(this.host);
		return solrClient;
	}
	
	public static void main(String[] args) throws SolrServerException,
			IOException {
		SolrClient solrClient = new SolrConfig().solrServer();
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.add("q", "*:*");
		QueryResponse response = solrClient.query(solrQuery);

		SolrDocumentList list = response.getResults();

		for (SolrDocument document : list) {
			Map<String, Object> map = document.getFieldValueMap();

			for (String key : map.keySet()) {
				System.out.println(key);
				System.out.println(map.get(key));
				if (key.equals("id")) {
					solrClient.deleteById((String) map.get(key));
					UpdateResponse updateResponse = solrClient.commit();
					System.out.println(updateResponse.getStatus());
				}

			}
		}
	}

}
