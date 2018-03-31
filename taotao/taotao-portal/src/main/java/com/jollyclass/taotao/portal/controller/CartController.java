package com.jollyclass.taotao.portal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.portal.pojo.CartItem;
import com.jollyclass.taotao.portal.service.CartService;

/**
 * @author 邹丹丹
 * @date 2017年8月1日 下午5:11:19
 * 
 */
@Controller
public class CartController {
	@Autowired
	private CartService cartService;

	@RequestMapping("/order/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("showOrderCart");
		List<CartItem> cartList = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", cartList);
		return "order-cart";
	}

	/**
	 * 添加购物车，跳转到添加成功页面
	 * 
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable long itemId, @RequestParam(defaultValue = "1") int num,
			HttpServletRequest request, HttpServletResponse response) {
		TaoTaoResult result = cartService.addCart(itemId, num, request, response);
		return "cartSuccess";
	}

	/**
	 * 查看购物车
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/cart/cart")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("showCart");
		List<CartItem> cartList = cartService.getCartItemList(request, response);
		System.out.println("商品总计：" + cartList.size());
		model.addAttribute("cartList", cartList);
		return "cart";
	}

	/**
	 * 删除购物车
	 * 
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCart(@PathVariable long itemId, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("deleteCart");
		cartService.deleteCart(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

	/**
	 * 修改购物车数量
	 * 
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cart/update/num/{itemId}", method = RequestMethod.POST)
	public String updateCartNum(@PathVariable long itemId, @RequestParam("num") int num, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("updateCartNum");
		cartService.updateCartNum(itemId, num, request, response);
		return "redirect:/cart/cart.html";
	}

	/**
	 * 更新数量
	 * 
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/service/cart/update/num/{itemId}", method = RequestMethod.POST)
	public String changeCartNum(@PathVariable long itemId, @RequestParam("num") int num, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("changeCartNum");
		cartService.changeCartNum(itemId, num, request, response);
		return "redirect:/cart/cart.html";
	}
	/**
	 * 返回修改购物车
	 * @return
	 */
	@RequestMapping("/cart/show")
	public String showCart(){
		return "redirect:/cart/cart.html";
	}
}
