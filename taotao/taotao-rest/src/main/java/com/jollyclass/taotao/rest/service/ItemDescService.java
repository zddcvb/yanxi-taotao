package com.jollyclass.taotao.rest.service;

import com.jollyclass.taotao.common.utils.TaoTaoResult;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午4:30:12
 * 
 */
public interface ItemDescService {
	/**
	 * 根据id查询商品描述信息
	 * @param itemId
	 * @return
	 */
	TaoTaoResult getItemDescInfo(Long itemId);
}
