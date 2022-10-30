package com.getir.ReadingIsGood.service.impl;

import com.getir.ReadingIsGood.dto.request.OrderRequestDto;
import com.getir.ReadingIsGood.dto.response.OrderResponseDto;
import com.getir.ReadingIsGood.entity.BookEntity;
import com.getir.ReadingIsGood.entity.OrderEntity;
import com.getir.ReadingIsGood.exception.ErrorType;
import com.getir.ReadingIsGood.exception.GlobalException;
import com.getir.ReadingIsGood.repository.BookRepository;
import com.getir.ReadingIsGood.repository.CustomerRepository;
import com.getir.ReadingIsGood.repository.OrderRepository;
import com.getir.ReadingIsGood.service.BookService;
import com.getir.ReadingIsGood.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @Transactional
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        logger.debug("***************************************");
        logger.debug("OrderRequestDto OrderID for create customer" + orderRequestDto.getOrderID());
        OrderEntity orderEntity = modelMapper.map(orderRequestDto, OrderEntity.class);

        if(!customerIsExist(orderEntity)){
            logger.error("Customer is not exist");
            throw new GlobalException(ErrorType.CUSTOMER_IS_NOT_EXIST_ERROR);
        }
        if(!bookIsExist(orderEntity)){
            logger.error("Book is not exist");
            throw new GlobalException(ErrorType.BOOK_IS_NOT_EXIST);
        }

        if(!bookAmountIsSufficient(orderEntity)){
            logger.error("Book amount is not sufficient");
            throw new GlobalException(ErrorType.BOOK_AMOUNT_IS_NOT_SUFFICIENT_ERROR);
        }

        if(orderEntity.getOrderAmount() <= 0){
            logger.error("Order amount is not suitable");
            throw new GlobalException(ErrorType.ORDER_AMOUNT_IS_NOT_SUITABLE_ERROR);
        }

        orderEntity.setCreatedDate(LocalDateTime.now());
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        updateBookStock(orderEntity);
        logger.debug("Saved Order" + savedOrder.getOrderID());
        return OrderResponseDto.builder()
                .orderID(savedOrder.getBookID())
                .orderAmount(savedOrder.getOrderAmount())
                .customerID(savedOrder.getCustomerID())
                .BookID(savedOrder.getBookID())
                .build();
    }

    @Override
    public OrderResponseDto getOrderDetails(long id) {
        logger.debug("***************************************");
        logger.debug("Order id for order details: " + id);
        if(!orderIsExist(id)){
            logger.error("Order is not exist");
            throw new GlobalException(ErrorType.ORDER_IS_NOT_EXIST_ERROR);
        }

        OrderEntity savedOrder = orderById(id);
        logger.debug("Saved Order" + savedOrder.getOrderID());

        return OrderResponseDto.builder()
                .orderID(savedOrder.getBookID())
                .orderAmount(savedOrder.getOrderAmount())
                .customerID(savedOrder.getCustomerID())
                .BookID(savedOrder.getBookID())
                .build();
    }

    @Override
    @Transactional
    public OrderResponseDto updateStock(OrderRequestDto orderRequestDto) {
        logger.debug("***************************************");
        logger.debug("OrderRequestDto OrderID for update stock" + orderRequestDto.getOrderID());
        OrderEntity orderEntity = modelMapper.map(orderRequestDto, OrderEntity.class);
        updateBookStock(orderEntity);
        return null;
    }

    @Override
    @Transactional
    public List<OrderEntity> getOrdersBetweenTwoDates(LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("***************************************");
        logger.debug("Get orders between to dates : " + startDate + "--" + endDate);
        LocalDateTime today = LocalDateTime.now();

        if(today.isBefore(endDate)){
            throw new GlobalException(ErrorType.DATE_IS_NOT_VALID_ERROR);
        }

        List<OrderEntity> orderResponseDtoList = orderRepository.findAllByCreatedDateBetween(startDate, endDate);
        return orderResponseDtoList;
    }


    private int updateBookStock(OrderEntity orderEntity){
        BookEntity bookEntity = bookRepository.getByBookID(orderEntity.getBookID());
        if(bookService.getBookQuantity(bookEntity) <= 0){
            throw new GlobalException(ErrorType.BOOK_IS_NOT_AVAILABLE_ERROR);
        }
        bookEntity.setQuantity(bookService.getBookQuantity(bookEntity) - orderEntity.getOrderAmount());
        bookService.updateQuantity(bookEntity);
        return 0;
    }

    private boolean bookIsExist(OrderEntity orderEntity){
        return bookRepository.existsById(orderEntity.getBookID());
    }

    private boolean customerIsExist(OrderEntity orderEntity){
        return customerRepository.existsById(orderEntity.getCustomerID());
    }
    private boolean bookAmountIsSufficient(OrderEntity orderEntity){
        return bookRepository.getByBookID(orderEntity.getBookID()).getQuantity() >= orderEntity.getOrderAmount();
    }
    private boolean orderIsExist(long id){
        return orderRepository.existsByOrderID(id);
    }

    private OrderEntity orderById(long id){
        return orderRepository.getByOrderID(id);
    }
}
