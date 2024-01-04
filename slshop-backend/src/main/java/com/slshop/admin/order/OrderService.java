package com.slshop.admin.order;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.slshop.common.entity.order.Order;

@Service
public class OrderService {
	private final OrderMapper orderMapper;
	
	@Autowired
	public OrderService(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
	
	public Page<Order> findAll(Pageable pageable){
		// 抽出行情報を作成(1ページ目だったら何こめから何こめまでを抽出するかのような)
		RowBounds rowBounds = new RowBounds(
                (int)pageable.getOffset(), pageable.getPageSize());
		
		// 戻り値はそれ用のインスタンスを作成
		return new PageImpl<>( this.orderMapper.findAll(rowBounds),pageable,orderMapper.count());
	}
}
