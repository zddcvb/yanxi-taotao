package com.jollyclass.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jollyclass.taotao.pojo.TbItem;
import com.jollyclass.taotao.portal.service.ItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午5:40:32
 * 
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable long itemId, Model model) {
		System.out.println("showItem");
		TbItem item = itemService.getItem(itemId);
		model.addAttribute("item", item);
		return "item";
	}
}
