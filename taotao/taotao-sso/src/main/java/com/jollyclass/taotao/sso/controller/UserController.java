package com.jollyclass.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.sso.service.UserService;

/**
 * @author 邹丹丹
 * @param <userService>
 * @date 2017年7月31日 上午10:19:40
 * 
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 校验数据是否存在
	 * 
	 * @param param
	 * @param type
	 * @param callback
	 * @return
	 */
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
		TaoTaoResult result = null;
		// 校验错误
		if (StringUtils.isBlank(param)) {
			result = TaoTaoResult.build(500, "参数不能为空");
		}
		if (type == null) {
			result = TaoTaoResult.build(500, "类型不能为空");
		}
		if (type != 1 && type != 2 && type != 3) {
			result = TaoTaoResult.build(500, "类型错误");
		}
		if (null != result) {
			if (null != callback) {
				MappingJacksonValue jacksonValue = new MappingJacksonValue(result.getData());
				jacksonValue.setJsonpFunction(callback);
				return jacksonValue;
			} else {
				return result;
			}
		}
		// 校验正确
		try {
			result = userService.checkData(param, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!StringUtils.isBlank(callback)) {
			MappingJacksonValue jacksonValue = new MappingJacksonValue(result.getData());
			jacksonValue.setJsonpFunction(callback);
			return jacksonValue;
		} else {
			return result;
		}
	}

	/**
	 * 注册用户
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param phone
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public TaoTaoResult register(String username, String password, String email, String phone) {
		TaoTaoResult result = null;
		try {
			result = userService.register(username, password, email, phone);
		} catch (Exception e) {
			e.printStackTrace();
			result = TaoTaoResult.build(400, "注册失败. 请校验数据后请再提交数据.");
		}
		return result;
	}

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public TaoTaoResult login(HttpServletRequest request, HttpServletResponse response, String username,
			String password) {
		TaoTaoResult result = null;
		if (StringUtils.isBlank(username)) {
			result = TaoTaoResult.build(400, "用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			result = TaoTaoResult.build(400, "密码不能为空");
		}
		try {
			result = userService.login(request, response, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			result = TaoTaoResult.build(400, "登录失败");
		}
		return result;
	}

	/**
	 * 根据token查询用户
	 * 
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object token(@PathVariable String token, String callback) {
		TaoTaoResult result = null;
		if (StringUtils.isBlank(token)) {
			result = TaoTaoResult.build(400, "查询token不能为空");
		}
		if (result != null) {
			if (callback != null) {
				MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
				jacksonValue.setJsonpFunction(callback);
				return jacksonValue;
			} else {
				return result;
			}
		}
		try {
			result = (TaoTaoResult) userService.token(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = TaoTaoResult.build(400, "查询token不存在");
		}
		if (callback != null) {
			MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
			return jacksonValue;
		} else {
			return result;
		}
	}

	/**
	 * 安全退出
	 * 
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("/logout/{token}")
	public Object logout(@PathVariable String token, String callback) {
		TaoTaoResult result = null;
		try {
			result = (TaoTaoResult) userService.logout(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = TaoTaoResult.build(400, "查询token不存在");
		}
		if (callback != null) {
			MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
			return jacksonValue;
		} else {
			return result;
		}
	}

	/**
	 * 注册页面
	 * 
	 * @return register
	 */
	@RequestMapping("/showRegister")
	public String showRegister() {
		return "register";
	}

	/**
	 * 注册页面
	 * 
	 * @return register
	 */
	@RequestMapping("/showLogin")
	public String showLogin(String redirectUrl,Model model) {
		model.addAttribute("redirectUrl", redirectUrl);
		return "login";
	}
}
