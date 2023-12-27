package com.slshop.cartitem;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.slshop.common.entity.CartItem;

@Mapper
public interface CartItemMapper {
	
	public void insert(CartItem cartItem);
	
	public CartItem findItem(Long customerId,Long productId);
	
	public void update(CartItem cartItem);
	
	public List<CartItem> findAll(Long customerId);
	
	public void deletedById(Long productId);
	
	public Integer sum(Long customerId);
}
