package com.slshop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.slshop.cartitem.CartItemService;
import com.slshop.common.entity.CartItem;
import com.slshop.common.entity.Modal;
import com.slshop.common.entity.product.Product;
import com.slshop.security.CustomerUserDetails;

@Controller
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	private final CartItemService cartItemService;

	@Autowired
	public ProductController(ProductService productService, CartItemService cartItemService) {
		this.productService = productService;
		this.cartItemService = cartItemService;
	}

	@GetMapping
	public String viewProducts(@ModelAttribute("modal") ModelMap modal, Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		Modal modalval = (Modal) modal.get("modal");
		model.addAttribute("modal", modalval);
		return "products/products";
	}

	@GetMapping("/detail/{productId}")
	public String viewProductDetail(@PathVariable("productId") Long productId, Model model,
			@ModelAttribute("modal") ModelMap modal) {
		Product product = productService.getProduct(productId);
		model.addAttribute("id", productId);
		model.addAttribute("product", product);
		Modal modalval = (Modal) modal.get("modal");
		model.addAttribute("modal", modalval);
		//        
		//        Modal aaa = new Modal();
		//        aaa.setTitle("サンプルで作成_ゆうじ");
		//        aaa.setMessage("サンプルメッセージ_ゆうじ");
		//        model.addAttribute("modal", aaa);
		return "products/product_detail";
	}

	@PostMapping("/detail/{productId}")
	public String insert(@AuthenticationPrincipal CustomerUserDetails customerUserDetails,
			@PathVariable("productId") Long productId, Integer quantity, Model model,
			RedirectAttributes redirectAttributes) {
		if (customerUserDetails != null) {
			Long customerId = customerUserDetails.getCustomer().getId();
			CartItem cartItem = this.cartItemService.findItem(customerId, productId);
			int updatedQuantity = 0;
			if (cartItem != null) {
				if (cartItem.getQuantity() + quantity >= 10) {
					Modal modal = new Modal();
					modal.setTitle("カートへ商品へ追加");
					modal.setMessage("商品を追加できませんでした。最大数量は10個です。(カート内:" + quantity + ")");
					model.addAttribute("modal", modal);
					ModelMap modelMap = new ModelMap();
					modelMap.addAttribute("modal", modal);
					redirectAttributes.addFlashAttribute("modal", modelMap);
					return "redirect:/products/detail/" + productId;
				}else {
					updatedQuantity = quantity + cartItem.getQuantity();
					this.cartItemService.update(customerId, productId, updatedQuantity);
				}
			}else {
				updatedQuantity = quantity;
				this.cartItemService.insert(customerId, productId, quantity);
			}
			Modal modal = new Modal();
			modal.setTitle("カートへ商品へ追加");
			modal.setMessage("商品をカートに追加しました(現在数量：" + updatedQuantity + ")");
			model.addAttribute("modal", modal);
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("modal", modal);
			redirectAttributes.addFlashAttribute("modal", modelMap);
			return "redirect:/products";
		} else {
			//    	         ログインしていない場合の処理
			Modal modal = new Modal();
			modal.setTitle("カートへ商品へ追加");
			modal.setMessage("ログインが必要です。");
			model.addAttribute("modal", modal);
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute("modal", modal);
			redirectAttributes.addFlashAttribute("modal", modelMap);
			return "redirect:/login";
		}
	}

}
