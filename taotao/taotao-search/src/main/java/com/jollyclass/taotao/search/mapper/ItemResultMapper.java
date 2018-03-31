package com.jollyclass.taotao.search.mapper;

import java.util.List;

import com.jollyclass.taotao.search.pojo.ItemResult;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午2:20:13
 * 
 */
public interface ItemResultMapper {
	/**
	 * 查询所有的数据
	 * @return list数据
	 */
	public List<ItemResult> getItemList();
}
