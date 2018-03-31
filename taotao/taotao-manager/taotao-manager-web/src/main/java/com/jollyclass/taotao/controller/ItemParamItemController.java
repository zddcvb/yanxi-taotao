package com.jollyclass.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jollyclass.taotao.service.ItemParamItemService;

/**
 * 商品规格参数详情控制层
 * @author 邹丹丹
 * @date 2017年7月20日 下午7:15:04
 * 
 */
@Controller
public class ItemParamItemController {
	@Autowired
	private ItemParamItemService itemParamItemService;
	/**
	 * 根据商品id展示商品规格参数
	 * @param itemId 商品id
	 * @param model 保存获取额规格参数
	 * @return 字符串，item页面
	 */
	@RequestMapping("/item/aa/{itemId}")
	public String getItemParamItemByItemId(@PathVariable Long itemId, Model model) {
		String string = itemParamItemService.getItemParamItemByItemId(itemId);
		model.addAttribute("item", string);
		return "item";

	}
}
