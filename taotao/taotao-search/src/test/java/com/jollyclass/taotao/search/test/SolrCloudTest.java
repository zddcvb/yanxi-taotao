package com.jollyclass.taotao.search.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;

/**
 * @author 邹丹丹
 * @date 2017年8月5日 下午4:11:29
 * 
 */
public class SolrCloudTest {
	/**
	 * 添加
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public void  testAddSolrServer() throws SolrServerException, IOException{
		String zkHost="192.16.13.128:2181,192.16.13.128:2182,192.16.13.128:2183";
		CloudSolrServer cloudSolrServer=new CloudSolrServer(zkHost);
		cloudSolrServer.setDefaultCollection("collection1");
		SolrInputDocument document=new SolrInputDocument();
		document.addField("id", "test000002");
		document.addField("item_title", "jack");				
		cloudSolrServer.add(document);
		cloudSolrServer.commit();
	}
	/**
	 * 删除
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public void deleteSolrServer() throws SolrServerException, IOException{
		String zkHost="192.16.13.128:2181,192.16.13.128:2182,192.16.13.128:2183";
		CloudSolrServer cloudSolrServer=new CloudSolrServer(zkHost);
		cloudSolrServer.deleteById("test000002");
		cloudSolrServer.deleteByQuery("*:*");
		cloudSolrServer.commit();
	}
	
}
