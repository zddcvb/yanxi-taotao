package com.jollyclass.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.portal.service.ItemParamItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午6:08:01
 * 
 */
@Controller
public class ItemParamItemController {
	@Autowired
	private ItemParamItemService itemParamItemService;

	/**
	 * 显示规格参数数据
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/item/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String showItemParamItem(@PathVariable long itemId) {
		System.out.println("======showItemParamItem========");
		String string = itemParamItemService.getItemParamItemByItemId(itemId);
		System.out.println("string:" + string);
		return string;
	}
}
