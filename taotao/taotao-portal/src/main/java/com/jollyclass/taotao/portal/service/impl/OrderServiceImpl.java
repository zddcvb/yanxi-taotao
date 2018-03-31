package com.jollyclass.taotao.portal.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.HttpClientUtils;
import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbOrder;
import com.jollyclass.taotao.portal.pojo.Order;
import com.jollyclass.taotao.portal.service.OrderService;

/**
 * @author 邹丹丹
 * @date 2017年8月4日 下午2:27:09
 * 
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	@Override
	public Long createOrder(Order order) {
		try {
			String jsonData = HttpClientUtils.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL,
					JsonUtils.objectToJson(order));
			System.out.println("jsonData:" + jsonData);
			if (jsonData != null) {
				TaoTaoResult result = TaoTaoResult.format(jsonData);
				int orderId = (int) result.getData();
				return (long) orderId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
