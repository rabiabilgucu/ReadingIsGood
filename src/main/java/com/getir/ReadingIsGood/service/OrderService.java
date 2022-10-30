package com.getir.ReadingIsGood.service;

import com.getir.ReadingIsGood.dto.request.OrderRequestDto;
import com.getir.ReadingIsGood.dto.response.OrderResponseDto;
import com.getir.ReadingIsGood.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    OrderResponseDto addOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto getOrderDetails(long id);

    OrderResponseDto updateStock(OrderRequestDto orderRequestDto);

    List<OrderEntity> getOrdersBetweenTwoDates(LocalDateTime startDate, LocalDateTime endDate);
}
