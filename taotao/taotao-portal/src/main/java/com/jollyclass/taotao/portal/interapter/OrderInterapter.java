package com.jollyclass.taotao.portal.interapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jollyclass.taotao.common.utils.CookieUtils;

/**
 * @author 邹丹丹
 * @date 2017年8月4日 下午4:27:58
 * 
 */
public class OrderInterapter implements HandlerInterceptor{

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
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		if (StringUtils.isBlank(token)) {
			response.sendRedirect("http://localhost:8084/login.html");
			return false;
		}
		return true;
	}

}
