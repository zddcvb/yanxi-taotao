package com.jollyclass.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.TaoTaoResult;

/**
 * @author 邹丹丹
 * @date 2017年7月31日 下午3:29:27
 * 
 */
@Controller
public class UserController {

	/**
	 * 注册页面
	 * 
	 * @return register
	 */
	@RequestMapping("/user/register")
	public String showRegister() {
		System.out.println("showRegister");
		return "register";
	}

	/**
	 * 登录页面
	 * 
	 * @return login
	 */
	@RequestMapping("/user/showLogin")
	public String showLogin() {
		System.out.println("showLogin");
		return "login";
	}

	@RequestMapping("/user/login")
	@ResponseBody
	public TaoTaoResult login(String username, String password) {

		// userService.login(username, password);
		return TaoTaoResult.ok("empty");
	}
}
