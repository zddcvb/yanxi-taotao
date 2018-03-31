package com.jollyclass.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jollyclass.taotao.search.dao.SearchDao;
import com.jollyclass.taotao.search.pojo.ItemResult;
import com.jollyclass.taotao.search.pojo.SearchResult;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午3:53:03
 * 
 */
@Repository
public class SearchDaoImpl implements SearchDao {
	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResult search(SolrQuery query) {
		try {
			SearchResult result = new SearchResult();
			//执行查询
			QueryResponse response = solrServer.query(query);
			//获得查询结果
			SolrDocumentList solrDocumentList = response.getResults();
			//获取查询的总数量
			long total = solrDocumentList.getNumFound();
			result.setTotalCount(total);
			List<ItemResult> itemResults = new ArrayList<ItemResult>();
			//获得高亮信息
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			
			//遍历查询结果
			for (SolrDocument solrDocument : solrDocumentList) {
				ItemResult itemResult = new ItemResult();
				itemResult.setId((String) solrDocument.get("id"));
				//获得高亮信息数据,此处传的solrDocument得到的值
				List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
				String title = null;
				if (list != null && list.size() > 0) {
					title = list.get(0);
				} else {
					title = (String) solrDocument.get("item_title");
				}
				//生成新的数据
				itemResult.setTitle(title);
				itemResult.setCategory_name((String) solrDocument.get("item_category_name"));
				itemResult.setImage((String) solrDocument.get("item_image"));
				itemResult.setItem_desc((String) solrDocument.get("item_desc"));
				itemResult.setPrice((Long) solrDocument.get("item_price"));
				itemResult.setSell_point((String) solrDocument.get("item_sell_point"));
				itemResults.add(itemResult);
			}
			result.setLists(itemResults);
			return result;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

}
