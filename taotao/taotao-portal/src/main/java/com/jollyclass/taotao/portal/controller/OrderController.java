package com.jollyclass.taotao.portal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jollyclass.taotao.portal.pojo.Order;
import com.jollyclass.taotao.portal.service.OrderService;

/**
 * @author 邹丹丹
 * @date 2017年8月2日 下午5:01:25
 * 
 */
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	/**
	 * 创建订单成功
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/order/create", method = RequestMethod.POST)
	public String createOrder(Order order,Model model) {
		System.out.println("createOrder");
		Long orderId = orderService.createOrder(order);
		System.out.println("orderId:" + orderId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("payment", order.getPayment());
		model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd HH:mm:ss"));
		return "success";
	}
}
