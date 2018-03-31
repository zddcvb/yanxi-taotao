package com.jollyclass.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jollyclass.taotao.common.utils.CookieUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.mapper.TbOrderItemMapper;
import com.jollyclass.taotao.mapper.TbOrderMapper;
import com.jollyclass.taotao.mapper.TbOrderShippingMapper;
import com.jollyclass.taotao.order.dao.JedisClient;
import com.jollyclass.taotao.order.pojo.Order;
import com.jollyclass.taotao.order.service.OrderService;
import com.jollyclass.taotao.pojo.TbOrder;
import com.jollyclass.taotao.pojo.TbOrderExample;
import com.jollyclass.taotao.pojo.TbOrderItem;
import com.jollyclass.taotao.pojo.TbOrderItemExample;
import com.jollyclass.taotao.pojo.TbOrderShipping;
import com.jollyclass.taotao.pojo.TbOrderShippingExample;

/**
 * @author 邹丹丹
 * @date 2017年8月1日 下午5:48:15
 * 
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	@Value("${ORDER_INIT_KEY}")
	private String ORDER_INIT_KEY;
	@Value("${ORDER_ITEM_GEN_KEY}")
	private String ORDER_ITEM_GEN_KEY;
	@Value("${ORDER_SHIPPING_KEY}")
	private String ORDER_SHIPPING_KEY;

	@Override
	public TaoTaoResult getOrderByOrderId(String orderId) {
		Order order = new Order();
		TbOrder tbOrder = orderMapper.selectByPrimaryKey(orderId);
		order.setOrderId(orderId);
		order.setPayment(tbOrder.getPayment());
		order.setStatus(tbOrder.getStatus());
		order.setCreateTime(tbOrder.getCreateTime());
		order.setPostFee(tbOrder.getPostFee());
		order.setUserId(tbOrder.getUserId());
		order.setBuyerMessage(tbOrder.getBuyerMessage());
		order.setBuyerNick(tbOrder.getBuyerNick());
		// 查询orderItem
		TbOrderItemExample example = new TbOrderItemExample();
		example.createCriteria().andOrderIdEqualTo(orderId);
		List<TbOrderItem> orderItems = orderItemMapper.selectByExample(example);
		// 查询orderShopping
		TbOrderShippingExample shippingExample = new TbOrderShippingExample();
		shippingExample.createCriteria().andOrderIdEqualTo(orderId);
		List<TbOrderShipping> shippings = orderShippingMapper.selectByExample(shippingExample);
		order.setOrderItems(orderItems);
		order.setOrderShipping(shippings.get(0));
		return TaoTaoResult.ok(order);
	}

	@Override
	public TaoTaoResult getOrderByUserId(long userId, int page, int count) {
		TbOrderExample example = new TbOrderExample();
		example.createCriteria().andUserIdEqualTo(userId);
		PageHelper.startPage(page, count);
		List<TbOrder> list = orderMapper.selectByExample(example);
		return TaoTaoResult.ok(list);
	}

	@Override
	public TaoTaoResult updateOrder(String orderId, int status, Date paymentTime) {
		TbOrder tbOrder = orderMapper.selectByPrimaryKey(orderId);
		tbOrder.setStatus(status);
		tbOrder.setPaymentTime(paymentTime);
		orderMapper.updateByPrimaryKey(tbOrder);
		return TaoTaoResult.ok();
	}

	@Override
	public TaoTaoResult addCart(TbOrder order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping) {
		// 通过redis的incr来进行
		String string = jedisClient.get(ORDER_GEN_KEY);
		System.out.println("string:" + string);
		if (StringUtils.isBlank(string)) {
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_KEY);
		}
		Long orderId = jedisClient.incr(ORDER_GEN_KEY);
		System.out.println("orderId:" + orderId);
		order.setOrderId(orderId + "");
		Date date = new Date();
		order.setStatus(1);
		order.setCreateTime(date);
		order.setUpdateTime(date);
		order.setBuyerRate(0);
		orderMapper.insert(order);

		// 创建订单商品详情
		for (TbOrderItem tbOrderItem : orderItems) {
			Long id = jedisClient.incr(ORDER_ITEM_GEN_KEY);
			tbOrderItem.setId(id + "");
			tbOrderItem.setOrderId(orderId + "");
			orderItemMapper.insert(tbOrderItem);
		}
		// 补全订单物流
		orderShipping.setOrderId(orderId + "");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		return TaoTaoResult.ok(orderId);
	}


}
