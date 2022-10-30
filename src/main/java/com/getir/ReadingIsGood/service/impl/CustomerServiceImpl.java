package com.getir.ReadingIsGood.service.impl;

import com.getir.ReadingIsGood.dto.request.CustomerRequestDto;
import com.getir.ReadingIsGood.dto.response.CustomerResponseDto;
import com.getir.ReadingIsGood.entity.CustomerEntity;
import com.getir.ReadingIsGood.entity.OrderEntity;
import com.getir.ReadingIsGood.exception.ErrorType;
import com.getir.ReadingIsGood.exception.GlobalException;
import com.getir.ReadingIsGood.repository.CustomerRepository;
import com.getir.ReadingIsGood.repository.OrderRepository;
import com.getir.ReadingIsGood.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger =
            LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Transactional
    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        logger.debug("***************************************");
        logger.debug("CustomerRequestDto CustomerID for create customer" + customerRequestDto.getId());
        CustomerEntity customerEntity = modelMapper.map(customerRequestDto, CustomerEntity.class);

        if (customerAlreadyExist(customerEntity)) {
            logger.error( "Customer is not exist");
            throw new GlobalException(ErrorType.CUSTOMER_ALREADY_EXIST_ERROR);
        }
        customerEntity.setCreatedDate(LocalDateTime.now());
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        logger.debug("Saved Customer :" + savedCustomer.getId());
        return CustomerResponseDto.builder()
                .name(savedCustomer.getName())
                .surname(savedCustomer.getSurname())
                .build();
    }

    @Override
    public Page<OrderEntity> getAllOrders(long id, Pageable pageable) {
        return orderRepository.findAllByCustomerID(id, pageable);
    }

    private boolean customerAlreadyExist(CustomerEntity customerEntity) {
        return customerRepository.existsByEmail(customerEntity.getEmail()) || customerRepository.existsByPhoneNumber(customerEntity.getPhoneNumber()) ;
    }

    public CustomerEntity getOneUserByUserName(String userName) {
        return customerRepository.findByName(userName);
    }

    public CustomerEntity saveOneUser(CustomerEntity newUser) {
        return customerRepository.save(newUser);
    }
}
