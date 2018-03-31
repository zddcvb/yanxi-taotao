package com.jollyclass.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jollyclass.taotao.common.utils.TaoTaoResult;

/**
 * @author 邹丹丹
 * @date 2017年7月31日 上午10:36:28
 * 
 */
public interface UserService {
	/**
	 * 根据参数校验数据
	 * @param param 参数内容
	 * @param type 类型
	 * @return object对象
	 */
	TaoTaoResult checkData(String param, Integer type);
	/**
	 * 注册用户
	 * @param tbUser
	 * @return
	 */
	TaoTaoResult register(String username, String password, String email, String phone);
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	TaoTaoResult login(HttpServletRequest request,HttpServletResponse response,String username, String password);
	/**
	 * 根据token查询用户
	 * @param token
	 * @return
	 */
	Object token(String token);
	/**
	 * 安全退出
	 * @param token
	 * @return
	 */
	TaoTaoResult logout(String token);

}
