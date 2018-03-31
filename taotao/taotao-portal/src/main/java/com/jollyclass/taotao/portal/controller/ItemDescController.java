package com.jollyclass.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.pojo.TbItemDesc;
import com.jollyclass.taotao.portal.service.ItemDescService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午5:58:54
 * 
 */
@Controller
public class ItemDescController {
	@Autowired
	private ItemDescService itemDescService;
	/**
	 * 获得商品描述信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String showItemDesc(@PathVariable long itemId) {
		System.out.println("showItemDesc");
		TbItemDesc itemDesc = itemDescService.getItemDescByID(itemId);
		String json = JsonUtils.objectToJson(itemDesc.getItemDesc());
		System.out.println("json:"+json);
		return json;
	}
}
