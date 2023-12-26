package com.slshop.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slshop.common.entity.Customer;

@Service
public class CustomerService {
	private final CustomerMapper customerMapper;
	
	@Autowired
	public CustomerService(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}
	
	public Customer findById(Long id) {
		return this.customerMapper.findById(id);
	}
	
}
