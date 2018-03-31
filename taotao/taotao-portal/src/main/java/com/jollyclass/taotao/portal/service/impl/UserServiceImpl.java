package com.jollyclass.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.HttpClientUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbUser;
import com.jollyclass.taotao.portal.service.UserService;

/**
 * @author 邹丹丹
 * @date 2017年7月31日 下午2:27:26
 * 
 */
@Service
public class UserServiceImpl implements UserService{
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	@Value("${GET_USER_BY_TOKEN}")
	private String GET_USER_BY_TOKEN;
	@Override
	public TbUser getUserByToken(String token) {
		String jsonData = HttpClientUtils.doGet(SSO_BASE_URL+GET_USER_BY_TOKEN+token);
		TaoTaoResult result = TaoTaoResult.formatToPojo(jsonData,TbUser.class);
		if (result.getStatus()==200) {
			TbUser user=(TbUser) result.getData();
			return user;
		}
		return null;
	}

}
