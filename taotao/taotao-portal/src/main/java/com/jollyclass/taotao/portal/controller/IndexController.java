package com.jollyclass.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jollyclass.taotao.portal.service.ContentService;

/**
 * @author 邹丹丹
 * @date 2017年7月21日 下午2:22:21
 * 
 */
@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;
	/**
	 * 展示首页
	 * @param model 用于传递数据给到jsp页面，json数据
	 * @return index页面
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		String ad1 = contentService.getContentList();
		model.addAttribute("ad1", ad1);
		return "index";
	}

	@RequestMapping(value = "/http/post", method = RequestMethod.POST)
	@ResponseBody
	public String showPost(String username, String password) {
		System.out.println("name:" + username + " password:" + password);
		return "name:" + username + " password:" + password;
	}
}
