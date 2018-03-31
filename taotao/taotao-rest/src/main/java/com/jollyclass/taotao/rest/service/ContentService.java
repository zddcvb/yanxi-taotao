package com.jollyclass.taotao.rest.service;

import java.util.List;

import com.jollyclass.taotao.pojo.TbContent;

/**
 * @author 邹丹丹
 * @date 2017年7月22日 下午2:42:03
 * 
 */
public interface ContentService {
	/**
	 * 根据分类id获得数据
	 * @param categoryId 分类的id
	 * @return list集合
	 */
	public List<TbContent> getContentList(long categoryId);
}
