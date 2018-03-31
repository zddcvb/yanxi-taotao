package com.jollyclass.taotao.rest.service;

import com.jollyclass.taotao.common.utils.TaoTaoResult;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午4:36:21
 * 
 */
public interface ItemParamItemService {
	/**
	 * 根据商品id查询商品规格参数
	 * @param itemId
	 * @return
	 */
	TaoTaoResult getItemParamInfo(long itemId);
}
