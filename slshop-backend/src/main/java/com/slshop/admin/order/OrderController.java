package com.slshop.admin.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slshop.common.entity.order.Order;

@Controller
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping
	public String orderPage(Pageable pageable,Model model) {
		//ページ情報も検索時に使用するように変更
		Page<Order> orders = this.orderService.findAll(pageable);
		model.addAttribute("orders", orders.getContent());
		//pageとして取り敢えず持たせる
		model.addAttribute("page", orders);
		return "orders";
	}
}
