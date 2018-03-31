package com.jollyclass.taotao.portal.service;
/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午6:04:18
 * 
 */
public interface ItemParamItemService {
	/**
	 * 根据商品id查询商品规格参数
	 * @param itemId 商品id
	 * @return 字符串数据
	 */
	public String getItemParamItemByItemId(long itemId);
}
