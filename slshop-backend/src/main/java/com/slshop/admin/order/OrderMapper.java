package com.slshop.admin.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.slshop.common.entity.order.Order;

@Mapper
public interface OrderMapper {

	public long count();
	public List<Order> findAll(RowBounds rowBounds);
}
