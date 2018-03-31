package com.jollyclass.taotao.solr;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 上午11:43:04
 * 
 */
public class SolrTest {
	/**
	 * 增加更新索引
	 * @throws SolrServerException
	 * @throws IOException
	 */
	@Test
	public void testAddSolr() throws SolrServerException, IOException {
		SolrServer solrServer = new HttpSolrServer("http://192.168.190.128:8080/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test01");
		document.addField("item_title", "手机测试01");
		solrServer.add(document);
		/*
		 * solrServer.add(List<SolrInputDocument> SolrInputDocuments);
		 * solrServer.add(SolrInputDocuments,1000);
		*/
		solrServer.commit();
	}
	/**
	 * 根据id删除数据
	 * @throws SolrServerException
	 * @throws IOException
	 */
	@Test
	public void testDelete_1() throws SolrServerException, IOException {
		SolrServer solrServer = new HttpSolrServer("http://192.168.190.128:8080/solr");
		solrServer.deleteById("test01");
		solrServer.commit();
	}
	/**
	 * 根据查询删除索引
	 * @throws SolrServerException
	 * @throws IOException
	 */
	@Test
	public void testDelete_2() throws SolrServerException, IOException {
		SolrServer solrServer = new HttpSolrServer("http://192.168.190.128:8080/solr");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
	/**
	 * 查询数据
	 * @throws SolrServerException
	 * @throws IOException
	 */
	@Test
	public void test_query() throws SolrServerException, IOException{
		SolrServer solrServer = new HttpSolrServer("http://192.168.190.128:8080/solr");
		SolrQuery solrquery=new SolrQuery("*:*");
		solrquery.set("fq", "item_title:jack");
		solrquery.set("fl", "item_desc");
		solrquery.setSort("id", ORDER.asc);
		solrquery.setStart(1);
		solrquery.setRows(30);
		solrquery.set("df", "item_keywords");
		solrquery.setHighlight(true);
		solrquery.addHighlightField("item_title");
		solrquery.setHighlightRequireFieldMatch(true);
		solrquery.setHighlightSimplePre("<em style='color:red'>");
		solrquery.setHighlightSimplePost("</em>");
		QueryResponse response = solrServer.query(solrquery);
		SolrDocumentList results = response.getResults();
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument solrDocument : results) {
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title = (String) solrDocument.get("item_title");
		}
		solrServer.commit();
	}
	
}
