package com.slshop.cartitem;

import org.apache.ibatis.annotations.Mapper;

import com.slshop.common.entity.CartItem;

@Mapper
public interface CartItemMapper {
	
	public void insert(CartItem cartItem);
}
