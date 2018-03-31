package com.jollyclass.taotao.order.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.order.pojo.Order;
import com.jollyclass.taotao.pojo.TbOrder;
import com.jollyclass.taotao.pojo.TbOrderItem;
import com.jollyclass.taotao.pojo.TbOrderShipping;

/**
 * @author 邹丹丹
 * @date 2017年8月1日 下午5:41:55
 * 
 */
public interface OrderService {

	/**
	 * 根据id查询订单信息
	 * 
	 * @param orderId
	 * @return
	 */
	TaoTaoResult getOrderByOrderId(String orderId);

	/**
	 * 根据用户分页信息查询订单
	 * 
	 * @param userId
	 * @param page
	 * @param count
	 * @return
	 */
	TaoTaoResult getOrderByUserId(long userId, int page, int count);

	/**
	 * 修改订单，修改支付状态、支付时间
	 * 
	 * @param orderId
	 * @return
	 */
	TaoTaoResult updateOrder(String orderId, int status, Date paymentTime);

	/**
	 * 根据id添加到订单中
	 * 
	 * @param itemId
	 * @return
	 */
	TaoTaoResult addCart(TbOrder order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping);

}
