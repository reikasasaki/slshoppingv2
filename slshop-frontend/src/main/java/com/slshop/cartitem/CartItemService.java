package com.slshop.cartitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slshop.common.entity.CartItem;
import com.slshop.customer.CustomerMapper;
import com.slshop.product.ProductMapper;

@Service
public class CartItemService {

	private final CartItemMapper cartItemMapper;
	private final CustomerMapper customerMapper;
	private final ProductMapper productMapper;
	
	@Autowired
	public CartItemService (CartItemMapper cartItemMapper,CustomerMapper customerMapper,ProductMapper productMapper) {
		this.cartItemMapper = cartItemMapper;
		this.customerMapper = customerMapper;
		this.productMapper = productMapper;
	}
	
	public void insert(Long customerId, Long productId, Integer quantity) {
		CartItem cartItem = new CartItem();
		cartItem.setCustomer(this.customerMapper.findById(customerId));
		cartItem.setProduct(this.productMapper.findById(productId));
		cartItem.setQuantity(quantity);
		this.cartItemMapper.insert(cartItem);
	}
	
	public void update(Long customerId,Long productId,Integer quantity) {
		CartItem cartItem = new CartItem();
		cartItem.setCustomer(this.customerMapper.findById(customerId));
		cartItem.setProduct(this.productMapper.findById(productId));
		cartItem.setQuantity(quantity);
		this.cartItemMapper.update(cartItem);
	}

	public CartItem findItem(Long customerId,Long productId) {
		return this.cartItemMapper.findItem(customerId, productId);
	}
}
