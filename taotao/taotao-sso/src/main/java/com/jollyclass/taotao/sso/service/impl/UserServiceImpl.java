package com.jollyclass.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.CookieUtils;
import com.jollyclass.taotao.common.utils.ExceptionUtils;
import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.mapper.TbUserMapper;
import com.jollyclass.taotao.pojo.TbUser;
import com.jollyclass.taotao.pojo.TbUserExample;
import com.jollyclass.taotao.pojo.TbUserExample.Criteria;
import com.jollyclass.taotao.sso.dao.JedisClient;
import com.jollyclass.taotao.sso.service.UserService;

/**
 * @author 邹丹丹
 * @date 2017年7月31日 上午10:37:53
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${REDIS_USER_EXPIRE_TIME}")
	private int REDIS_USER_EXPIRE_TIME;

	@Override
	public TaoTaoResult checkData(String param, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if (type == 1) {
			// 判断username
			criteria.andUsernameEqualTo(param);
		} else if (type == 2) {
			// 判断phone
			criteria.andPhoneEqualTo(param);
		} else if (type == 3) {
			// 判断email
			criteria.andEmailEqualTo(param);
		}
		List<TbUser> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return TaoTaoResult.ok(false);
		}
		return TaoTaoResult.ok(true);
	}

	@Override
	public TaoTaoResult register(String username, String password, String email, String phone) {
		Date date = new Date();
		TbUser user = new TbUser();
		user.setUsername(username);
		user.setPassword(DigestUtils.md5Hex(password));
		user.setEmail(email);
		user.setPhone(phone);
		user.setCreated(date);
		user.setUpdated(date);
		userMapper.insert(user);
		return TaoTaoResult.ok();
	}

	@Override
	public TaoTaoResult login(HttpServletRequest request, HttpServletResponse response, String username,
			String password) {
		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbUser tbUser = list.get(0);
			if (!DigestUtils.md5Hex(password).equals(tbUser.getPassword())) {
				return TaoTaoResult.build(400, "密码错误");
			}
			// 生成token信息
			String token = UUID.randomUUID().toString();
			// 保存缓存
			jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(tbUser));
			jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, REDIS_USER_EXPIRE_TIME);
			//设置cookie
			CookieUtils.setCookie(request, response, "TT_TOKEN", token);
			return TaoTaoResult.ok(token);
		} else {
			return TaoTaoResult.build(400, "用户不存在");
		}
	}

	@Override
	public Object token(String token) {
		// 从缓存中去token
		try {
			String jsonData = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
			if (jsonData != null) {
				TbUser tbUser = JsonUtils.jsonToPojo(jsonData, TbUser.class);
				return TaoTaoResult.ok(tbUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return TaoTaoResult.build(400, ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	@Override
	public TaoTaoResult logout(String token) {
		if (StringUtils.isBlank(token)) {
			return TaoTaoResult.build(400, "token不能为空");
		}
		// 查询缓存的数据，是否纯在token
		try {
			String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
			if (json != null) {
				TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
				if (tbUser != null) {
					// 不为空，则删除token
					jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
					return TaoTaoResult.ok();
				}
			} else {
				return TaoTaoResult.build(400, "数据不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
