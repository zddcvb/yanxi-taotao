package com.jollyclass.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.search.service.ItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午2:57:01
 * 
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping("/importAll")
	@ResponseBody
	public TaoTaoResult importAllIndex() {
		TaoTaoResult result = itemService.importAllIndex();
		return result;
	}
}
