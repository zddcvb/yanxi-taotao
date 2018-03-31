package com.jollyclass.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.jollyclass.taotao.search.pojo.SearchResult;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午3:51:38
 * 
 */
public interface SearchDao {
	/**
	 * 查询数据，并带高亮信息
	 * @param query  查询对象
	 * @return SearchResult封装对象
	 */
	public SearchResult search(SolrQuery query);
}
