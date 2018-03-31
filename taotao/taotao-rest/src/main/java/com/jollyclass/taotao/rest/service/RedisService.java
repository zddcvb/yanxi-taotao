package com.jollyclass.taotao.rest.service;

import com.jollyclass.taotao.common.utils.TaoTaoResult;

/**
 * redis缓存的业务层
 * @author 邹丹丹
 * @date 2017年7月25日 下午1:29:05
 * 
 */
public interface RedisService {
	/**
	 * 根据分类id，删除redis的内容
	 * @param categoryId 需要删除的分类id
	 * @return TaoTaoResult对象
	 */
	public TaoTaoResult syncContent(long categoryId);
}
