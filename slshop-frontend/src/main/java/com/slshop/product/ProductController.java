package com.slshop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slshop.cartitem.CartItemService;
import com.slshop.common.entity.product.Product;
import com.slshop.security.CustomerUserDetails;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CartItemService cartItemService;

    @Autowired
    public ProductController(ProductService productService,CartItemService cartItemService) {
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    
    @GetMapping
    public String viewProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "products/products";
    }

    @GetMapping("/detail/{productId}")
    public String viewProductDetail(@PathVariable("productId") Long productId, Model model) {
        Product product = productService.getProduct(productId);
        model.addAttribute("id", productId);
        model.addAttribute("product", product);
        return "products/product_detail";
    }
    
    @PostMapping("/detail/{productId}")
    public String insert(@AuthenticationPrincipal CustomerUserDetails customerUserDetails,@PathVariable("productId") Long productId,Integer quantity) {
    	 if (customerUserDetails != null) {
    	        Long customerId = customerUserDetails.getCustomer().getId();
    	        this.cartItemService.insert(customerId, productId, quantity);
    	        return "redirect:/products";
    	    } else {
    	        // ログインしていない場合の処理
    	        return "redirect:/login";
    	    }
    }
    
}
