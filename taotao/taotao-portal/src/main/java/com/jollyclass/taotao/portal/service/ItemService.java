package com.jollyclass.taotao.portal.service;

import com.jollyclass.taotao.pojo.TbItem;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午5:31:23
 * 
 */
public interface ItemService {
	/**
	 * 通过http获得item信息
	 * @param itemId 商品id
	 * @return TbItem对象
	 */
	public TbItem getItem(long itemId);

}
