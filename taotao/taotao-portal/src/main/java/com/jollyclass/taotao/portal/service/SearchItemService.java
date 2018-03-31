package com.jollyclass.taotao.portal.service;

import com.jollyclass.taotao.protal.pojo.SearchResult;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午5:37:46
 * 
 */
public interface SearchItemService {
	/**
	 * 调用solr搜索服务查询数据
	 * @param query
	 * @param page
	 * @return
	 */
	SearchResult search(String query, int page);

}
