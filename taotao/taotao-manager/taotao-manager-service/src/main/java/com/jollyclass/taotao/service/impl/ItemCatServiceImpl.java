package com.jollyclass.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.mapper.TbItemCatMapper;
import com.jollyclass.taotao.pojo.TbItemCat;
import com.jollyclass.taotao.pojo.TbItemCatExample;
import com.jollyclass.taotao.service.ItemCatService;

/**
 * @author 邹丹丹
 * @date 2017年7月17日 下午2:21:57
 * 
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public List<TbItemCat> getItemCatList(Long parentId) {
		TbItemCatExample example=new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		List<TbItemCat> itemCatList = itemCatMapper.selectByExample(example);
		return itemCatList;
	}

}
