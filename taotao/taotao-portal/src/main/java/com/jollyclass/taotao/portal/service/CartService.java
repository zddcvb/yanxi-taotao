package com.jollyclass.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.portal.pojo.CartItem;

/**
 * @author 邹丹丹
 * @date 2017年8月2日 上午10:55:58
 * 
 */
public interface CartService {
	/**
	 * 点击添加购物车，将商品添加至缓存
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	TaoTaoResult addCart(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	/**
	 * 从缓存中获得购物车列表
	 * @param request
	 * @param response
	 * @return
	 */
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 刪除cookie中的商品
	 * @param itemId
	 * @param request
	 * @param response
	 */
	void deleteCart(long itemId, HttpServletRequest request, HttpServletResponse response);
	/**
	 * 通过加减更新数量
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 */
	TaoTaoResult updateCartNum(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	/**
	 * 直接输入数量，更新cookie中的数据
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	TaoTaoResult changeCartNum(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

}
