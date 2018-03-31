package com.jollyclass.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.rest.service.ItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午4:27:21
 * 
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	/**
	 * 发布获取商品基本信息的服务
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/itemInfo/{itemId}")
	@ResponseBody
	public TaoTaoResult getItemBaseInfo(@PathVariable Long itemId) {
		TaoTaoResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
}
