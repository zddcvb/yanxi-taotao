package com.jollyclass.taotao.portal.service;

import com.jollyclass.taotao.pojo.TbUser;

/**
 * @author 邹丹丹
 * @date 2017年7月31日 下午2:15:25
 * 
 */
public interface UserService {
	/**
	 * 根据token取得用户信息
	 * @param token
	 * @return
	 */
	public TbUser getUserByToken(String token);
}
