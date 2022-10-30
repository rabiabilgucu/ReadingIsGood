package com.getir.ReadingIsGood.controller;

import com.getir.ReadingIsGood.dto.request.CustomerRequestDto;
import com.getir.ReadingIsGood.dto.response.CustomerResponseDto;
import com.getir.ReadingIsGood.dto.response.OrderResponseDto;
import com.getir.ReadingIsGood.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/createCustomer")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto customerResponse = customerService.createCustomer(customerRequestDto);
        return new ResponseEntity(customerResponse, HttpStatus.OK);
    }

    @GetMapping("/orderDetails/{id}")
    public ResponseEntity<Page<OrderResponseDto>> getCustomerOrders(@PathVariable long id,
                                                                    Pageable pageable) {

        return new ResponseEntity(customerService.getAllOrders(id, pageable), HttpStatus.OK);
    }
}
