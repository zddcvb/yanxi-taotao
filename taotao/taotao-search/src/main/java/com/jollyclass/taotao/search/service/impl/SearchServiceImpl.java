package com.jollyclass.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jollyclass.taotao.search.dao.SearchDao;
import com.jollyclass.taotao.search.pojo.SearchResult;
import com.jollyclass.taotao.search.service.SearchService;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午3:38:52
 * 
 */
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String query, int page, int rows) {
		// 根据条件查询
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(query);
		// 设置分页信息
		solrQuery.setStart((page - 1) * rows);
		solrQuery.setRows(rows);
		// 设置默认查询域
		solrQuery.set("df", "item_keywords");
		// 设置高亮信息
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		SearchResult searchResult = searchDao.search(solrQuery);
		// 设置分页信息
		long totalCount = searchResult.getTotalCount();
		System.out.println(totalCount);
		searchResult.setCurrentPage(page);
		int pageCount = (int) (totalCount / rows);
		if (totalCount % rows > 0) {
			pageCount++;
		}
		searchResult.setPageCount(pageCount);
		return searchResult;
	}

}
