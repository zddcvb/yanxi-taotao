package com.jollyclass.taotao.portal.service;

import com.jollyclass.taotao.pojo.TbItemDesc;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午5:54:49
 * 
 */
public interface ItemDescService {
	/**
	 * 根据商品id查询商品描述信息
	 * @param itemId
	 * @return
	 */
	TbItemDesc getItemDescByID(long itemId);
}
