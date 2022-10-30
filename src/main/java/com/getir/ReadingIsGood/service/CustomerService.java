package com.getir.ReadingIsGood.service;

import com.getir.ReadingIsGood.dto.request.CustomerRequestDto;
import com.getir.ReadingIsGood.dto.response.CustomerResponseDto;
import com.getir.ReadingIsGood.entity.CustomerEntity;
import com.getir.ReadingIsGood.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

    Page<OrderEntity> getAllOrders(long id, Pageable pageable);

    CustomerEntity getOneUserByUserName(String userName);

    CustomerEntity saveOneUser(CustomerEntity customerEntity);
}
