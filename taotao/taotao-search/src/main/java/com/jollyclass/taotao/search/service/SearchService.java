package com.jollyclass.taotao.search.service;

import com.jollyclass.taotao.search.pojo.SearchResult;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午3:36:18
 * 
 */
public interface SearchService {
	/**
	 * 查询数据，并封装成SearchResult对象，带分页
	 * @param query
	 * @param page
	 * @param rows
	 * @return
	 */
	SearchResult search(String query, int page, int rows);

}
