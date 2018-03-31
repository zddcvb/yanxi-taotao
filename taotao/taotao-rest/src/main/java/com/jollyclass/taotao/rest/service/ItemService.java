package com.jollyclass.taotao.rest.service;

import com.jollyclass.taotao.common.utils.TaoTaoResult;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午4:24:08
 * 
 */
public interface ItemService {
	/**
	 * 获取商品基本信息
	 * @param itemId 商品id
	 * @return TaoTaoResult对象
	 */
	TaoTaoResult getItemBaseInfo(Long itemId);
}
