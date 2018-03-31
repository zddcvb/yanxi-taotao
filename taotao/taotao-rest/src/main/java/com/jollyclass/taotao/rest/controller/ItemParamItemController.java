package com.jollyclass.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.rest.service.ItemParamItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午4:38:22
 * 
 */
@Controller
public class ItemParamItemController {
	@Autowired
	private ItemParamItemService itemParamItemService;

	/**
	 * 发布商品规格参数服务
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public TaoTaoResult getItemParamInfo(@PathVariable long itemId) {
		TaoTaoResult result = itemParamItemService.getItemParamInfo(itemId);
		return result;
	}
}
