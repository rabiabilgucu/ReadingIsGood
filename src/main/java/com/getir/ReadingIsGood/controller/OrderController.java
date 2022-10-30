package com.getir.ReadingIsGood.controller;

import com.getir.ReadingIsGood.dto.request.OrderRequestDto;
import com.getir.ReadingIsGood.dto.response.OrderResponseDto;
import com.getir.ReadingIsGood.entity.OrderEntity;
import com.getir.ReadingIsGood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseEntity<String> addOrder(@Valid @RequestBody OrderRequestDto orderRequestDto){
        OrderResponseDto orderResponse = orderService.addOrder(orderRequestDto);
        return new ResponseEntity(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/orderDetails/{id}")
    public ResponseEntity<String> getOrderDetails(@PathVariable long id){
        OrderResponseDto order = orderService.getOrderDetails(id);
        return new ResponseEntity(order, HttpStatus.OK);
    }

    @PostMapping("/updateStock")
    public ResponseEntity<String> updateStock(@Valid @RequestBody OrderRequestDto orderRequestDto){
        OrderResponseDto orderResponse = orderService.updateStock(orderRequestDto);
        return new ResponseEntity(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/getOrders/{startDate}/{endDate}")
    public ResponseEntity<String> getOrdersBetweenTwoDates(@PathVariable(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startDate,
                                                           @PathVariable(value = "endDate")@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endDate){
        List<OrderEntity> orderResponse = orderService.getOrdersBetweenTwoDates(startDate, endDate);
        return new ResponseEntity(orderResponse, HttpStatus.OK);
    }

}
