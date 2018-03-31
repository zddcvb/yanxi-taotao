package com.jollyclass.taotao.portal.service;

import javax.servlet.http.HttpServletRequest;

import com.jollyclass.taotao.portal.pojo.Order;

/**
 * @author 邹丹丹
 * @date 2017年8月2日 下午6:09:27
 * 
 */
public interface OrderService {
	/**
	 * 创建订单，返回订单号
	 * @param order
	 * @return
	 */
	public Long createOrder(Order order);

}
