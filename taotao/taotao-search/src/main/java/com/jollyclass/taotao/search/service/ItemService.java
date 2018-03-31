package com.jollyclass.taotao.search.service;

import com.jollyclass.taotao.common.utils.TaoTaoResult;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午2:29:17
 * 
 */
public interface ItemService {
	/**
	 * 查询所有，并将保存到索引库中
	 * @return
	 */
	TaoTaoResult importAllIndex();
}
