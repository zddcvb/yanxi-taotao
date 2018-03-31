package com.jollyclass.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jollyclass.taotao.portal.service.SearchItemService;
import com.jollyclass.taotao.protal.pojo.SearchResult;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午3:23:06
 * 
 */
@Controller
public class SearchItemController {
	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/search")
	public String search(@RequestParam("q") String query, @RequestParam(defaultValue = "1") int page, Model model) {
		System.out.println("query:" + query);
		if (!StringUtils.isBlank(query)) {
			try {
				query = new String(query.getBytes("iso8859-1"), "utf-8");
				System.out.println("第一次转码query:" + query);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		try {
			SearchResult result = searchItemService.search(query, page);
			model.addAttribute("query", query);
			model.addAttribute("page", page);
			model.addAttribute("itemList", result.getLists());
			model.addAttribute("totalPages", result.getTotalCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "search";
	}
}
