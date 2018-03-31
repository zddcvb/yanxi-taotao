package com.jollyclass.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.rest.service.ItemDescService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午4:32:57
 * 
 */
@Controller
public class ItemDescController {
	@Autowired
	private ItemDescService itemDescService;
	/**
	 * 发布查询描述信息服务
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaoTaoResult getItemDescInfo(@PathVariable Long itemId){
		TaoTaoResult result = itemDescService.getItemDescInfo(itemId);
		return result;
	}
}
