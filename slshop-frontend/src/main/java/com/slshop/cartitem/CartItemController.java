package com.slshop.cartitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slshop.common.entity.CartItem;
import com.slshop.security.CustomerUserDetails;

@Controller
@RequestMapping("/cart")
public class CartItemController {
	private final CartItemService cartItemService;
	
	@Autowired
	public CartItemController(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
	@GetMapping
	public String cartItemList(Model model,
			@AuthenticationPrincipal CustomerUserDetails customerUserDetails) {
		Long customerId = customerUserDetails.getCustomer().getId();
		List<CartItem> cartItems = this.cartItemService.findAll(customerId);
		Integer sum = this.cartItemService.sum(customerId);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("sum", sum);
		return "cart";
	}
	
	@PostMapping("delete/{id}")
	public String deletedById(@PathVariable("id") Long productId) {
		this.cartItemService.deletedById(productId);
		return "redirect:/cart";
	}
}
