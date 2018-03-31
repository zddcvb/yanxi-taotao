package com.jollyclass.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.rest.pojo.CatResult;
import com.jollyclass.taotao.rest.service.ItemCatService;

/**
 * @author 邹丹丹
 * @date 2017年7月21日 下午5:22:43
 * 
 */
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 获取商品分类表，并已json传递给前台，同步添加callback
	 * 
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/itemcat/all", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		String json = JsonUtils.objectToJson(catResult);
		String result = callback + "(" + json + ");";
		return result;
	}

	/**
	 * 获取商品分类表，并已json传递给前台，同步添加callback
	 * 采用MappingJacksonValue来实现，会自动拼接，但springMvc4.0以上才能用
	 * @param callback
	 * @return
	 */
	@RequestMapping("/itemCats/list4")
	@ResponseBody
	public Object getItemCatList4(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue jacksonValue = new MappingJacksonValue(catResult);
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
	}
}
