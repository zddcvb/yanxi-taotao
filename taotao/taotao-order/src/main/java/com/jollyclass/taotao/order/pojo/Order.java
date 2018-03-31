package com.jollyclass.taotao.order.pojo;

import java.util.List;

import com.jollyclass.taotao.pojo.TbOrder;
import com.jollyclass.taotao.pojo.TbOrderItem;
import com.jollyclass.taotao.pojo.TbOrderShipping;

/**
 * @author 邹丹丹
 * @date 2017年8月2日 上午9:40:46
 * 
 */
public class Order extends TbOrder {
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

}
