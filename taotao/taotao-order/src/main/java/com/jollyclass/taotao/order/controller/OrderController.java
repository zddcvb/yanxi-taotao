package com.jollyclass.taotao.order.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.order.pojo.Order;
import com.jollyclass.taotao.order.service.OrderService;

/**
 * @author 邹丹丹
 * @date 2017年8月1日 下午5:33:56
 * 
 */
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	/**
	 * 发布新增订单服务
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public TaoTaoResult addCart(@RequestBody Order order) {
		System.out.println("addCart");
		TaoTaoResult result = orderService.addCart(order, order.getOrderItems(), order.getOrderShipping());
		System.out.println("result:" + result.getData());
		return result;
	}

	/**
	 * 发布根据订单id查询订单服务
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/info/{orderId}")
	@ResponseBody
	public TaoTaoResult getOrderByOrderId(@PathVariable String orderId) {
		TaoTaoResult result = orderService.getOrderByOrderId(orderId);
		return result;
	}

	/**
	 * 发布根据用户分页信息查询订单服务
	 * 
	 * @param userId
	 * @param page
	 * @param count
	 * @return
	 */
	@RequestMapping("/list/{userID}/{page}/{count}")
	@ResponseBody
	public TaoTaoResult getOrderByUserId(@PathVariable long userId, @PathVariable int page, @PathVariable int count) {
		TaoTaoResult result = orderService.getOrderByUserId(userId, page, count);
		return result;
	}

	/**
	 * 发布修改订单的服务
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/changeStatus/{orderId}")
	@ResponseBody
	public TaoTaoResult updateOrder(@PathVariable String orderId, @PathVariable int status,
			@PathVariable Date paymentTime) {
		TaoTaoResult result = orderService.updateOrder(orderId, status, paymentTime);
		return result;
	}
}
