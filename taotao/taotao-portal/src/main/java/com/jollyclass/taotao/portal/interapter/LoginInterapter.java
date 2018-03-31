package com.jollyclass.taotao.portal.interapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jollyclass.taotao.common.utils.CookieUtils;
import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.pojo.TbUser;
import com.jollyclass.taotao.portal.service.UserService;

/**
 * @author 邹丹丹
 * @date 2017年7月31日 下午5:50:56
 * 
 */
public class LoginInterapter implements HandlerInterceptor {
	@Autowired
	private UserService userService;
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String json = CookieUtils.getCookieValue(request, "TT_TOKEN");
		if (StringUtils.isBlank(json)) {
			// 跳转到首页：http://localhost:8082
			//response.sendRedirect(userService.);
		} else {
			//拼接redirectUrl
			TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
		}

		return false;
	}
	public String getBaseUrl(HttpServletRequest request){
		String url=request.getScheme()+ "://" 
				+ request.getServerName() 
				+ ":"
				+ request.getServerPort() 
				+ request.getContextPath() 
				+ request.getRequestURI();
		return url;
	}

}
