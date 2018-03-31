package com.jollyclass.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbItemCat;
import com.jollyclass.taotao.service.ItemCatService;

/**
 * 商品类目展示功能
 * @author 邹丹丹
 * @date 2017年7月17日 下午2:19:27
 * 
 */
@Controller
@RequestMapping("/item/cat/")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	/**
	 * 向前台传递商品类目
	 * @param parentId item的id
	 * @return list集合传递到前台，会自动转换成json数据
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/list")
	@ResponseBody
	public List catagoryList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List list=new ArrayList<>();
		List<TbItemCat> itemCatList = itemCatService.getItemCatList(parentId);
		for (TbItemCat tbItemCat : itemCatList) {
			Map node=new HashMap<>();
			node.put("id", tbItemCat.getId());
			node.put("text", tbItemCat.getName());
			node.put("state", tbItemCat.getIsParent()?"closed":"opened");
			list.add(node);
		}
		return list;
	}
	
	
}
