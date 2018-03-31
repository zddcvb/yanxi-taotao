package com.jollyclass.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.CookieUtils;
import com.jollyclass.taotao.common.utils.HttpClientUtils;
import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbItem;
import com.jollyclass.taotao.portal.pojo.CartItem;
import com.jollyclass.taotao.portal.service.CartService;

/**
 * @author 邹丹丹
 * @date 2017年8月2日 上午10:56:59
 * 
 */
@Service
public class CartServiceImpl implements CartService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_BASE_INFO}")
	private String ITEM_BASE_INFO;

	@Override
	public TaoTaoResult addCart(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {

		CartItem ctt = null;
		// 从cookie中取出数据，判断当前数据是否存在，存在则增加数量
		List<CartItem> cartItemList = getCartItemList(request);
		for (CartItem cartItem : cartItemList) {
			if (cartItem.getId() == itemId) {
				cartItem.setNum(cartItem.getNum() + num);
				cartItem = ctt;
				break;
			}
		}
		if (ctt == null) {
			ctt = new CartItem();
			// 获得商品基本信息，封存到cartItem中,调用rest服务
			String jsonData = HttpClientUtils.doGet(REST_BASE_URL + ITEM_BASE_INFO + itemId);
			System.out.println("jsonData:" + jsonData);
			if (!StringUtils.isBlank(jsonData)) {
				TaoTaoResult result = TaoTaoResult.formatToPojo(jsonData, TbItem.class);
				TbItem tbItem = (TbItem) result.getData();
				ctt.setId(tbItem.getId());
				ctt.setImage(tbItem.getImage());
				ctt.setTitle(tbItem.getTitle());
				ctt.setPrice(tbItem.getPrice());
				ctt.setNum(num);
			}
			cartItemList.add(ctt);
			System.out.println("cartItemList:" + cartItemList);
		}

		// 重新保存到cookie中
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartItemList));
		// 返回数据到页面中
		return TaoTaoResult.ok();
	}

	public List<CartItem> getCartItemList(HttpServletRequest request) {
		String jsonData = CookieUtils.getCookieValue(request, "TT_CART");
		if (StringUtils.isBlank(jsonData)) {
			return new ArrayList<>();
		}
		try {
			List<CartItem> list = JsonUtils.jsonToList(jsonData, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItemList = getCartItemList(request);
		return cartItemList;
	}

	@Override
	public void deleteCart(long itemId, HttpServletRequest request, HttpServletResponse response) {
		String jsonData = CookieUtils.getCookieValue(request, "TT_CART");
		if (!StringUtils.isBlank(jsonData)) {
			List<CartItem> cartItems = JsonUtils.jsonToList(jsonData, CartItem.class);
			for (CartItem cartItem : cartItems) {
				if (cartItem.getId() == itemId) {
					cartItems.remove(cartItem);
					break;
				}
			}
			// 重新保存到cookie中
			CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartItems));
		}

	}

	@Override
	public TaoTaoResult updateCartNum(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		String jsonData = CookieUtils.getCookieValue(request, "TT_CART");
		if (!StringUtils.isBlank(jsonData)) {
			List<CartItem> cartItems = JsonUtils.jsonToList(jsonData, CartItem.class);
			for (CartItem cartItem : cartItems) {
				if (cartItem.getId() == itemId) {
					cartItem.setNum(num);
					break;
				}
			}
			// 重新保存到cookie中
			CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartItems));
		}
		return TaoTaoResult.ok();
	}

	@Override
	public TaoTaoResult changeCartNum(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		String jsonData = CookieUtils.getCookieValue(request, "TT_CART");
		if (!StringUtils.isBlank(jsonData)) {
			List<CartItem> cartItems = JsonUtils.jsonToList(jsonData, CartItem.class);
			for (CartItem cartItem : cartItems) {
				if (cartItem.getId() == itemId) {
					cartItem.setNum(num);
					break;
				}
			}
			// 重新保存到cookie中
			CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartItems));
		}
		return TaoTaoResult.ok();
	}

}
