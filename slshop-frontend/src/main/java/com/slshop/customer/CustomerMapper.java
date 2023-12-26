package com.slshop.customer;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.slshop.common.entity.Customer;

@Mapper
public interface CustomerMapper {
    Optional<Customer> findByEmail(String email);
    
    Customer findById(Long id);
    
}
