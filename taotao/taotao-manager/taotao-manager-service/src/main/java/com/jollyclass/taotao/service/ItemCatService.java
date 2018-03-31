package com.jollyclass.taotao.service;

import java.util.List;

import com.jollyclass.taotao.pojo.TbItemCat;

/**
 * 商品分类的业务层
 * @author 邹丹丹
 * @date 2017年7月17日 下午2:20:02
 * 
 */
public interface ItemCatService {
	/**
	 * 根据parentId查找商品分类信息
	 * @param parentId 商品id
	 * @return 商品分类的list集合
	 */
	List<TbItemCat> getItemCatList(Long parentId);
}
