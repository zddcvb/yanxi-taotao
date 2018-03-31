package com.jollyclass.taotao.service;
/**
 * @author 邹丹丹
 * @date 2017年7月20日 下午7:02:13
 * 
 */
public interface ItemParamItemService {
	/**
	 * 根据商品id查询商品规格参数
	 * @param itemId 商品id
	 * @return 字符串，商品规格参数数据
	 */
	public String getItemParamItemByItemId(long itemId);
}
