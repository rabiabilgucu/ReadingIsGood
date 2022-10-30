package com.getir.ReadingIsGood.serviceTest;

import com.getir.ReadingIsGood.ReadingIsGoodApplication;

import com.getir.ReadingIsGood.dto.request.OrderRequestDto;
import com.getir.ReadingIsGood.dto.response.OrderResponseDto;
import com.getir.ReadingIsGood.entity.BookEntity;
import com.getir.ReadingIsGood.entity.CustomerEntity;
import com.getir.ReadingIsGood.entity.OrderEntity;
import com.getir.ReadingIsGood.repository.BookRepository;
import com.getir.ReadingIsGood.repository.CustomerRepository;
import com.getir.ReadingIsGood.repository.OrderRepository;

import com.getir.ReadingIsGood.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = ReadingIsGoodApplication.class)
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderService orderService;

    BookEntity bookEntity;

    CustomerEntity customerEntity;

    @BeforeEach
    public void init(){
        createCustomerHelper();
        createBookHelper();
    }

    @Test
    @Transactional
    public void addOrderTest() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderAmount(1L);
        orderEntity.setOrderID(1L);
        orderEntity.setCustomerID(1L);
        orderEntity.setBookID(1L);

        orderRepository.save(orderEntity);

        Assert.assertTrue(orderRepository.existsByOrderID(orderEntity.getOrderID()));
    }

    @Test
    @Transactional
    public void getOrderDetail() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderAmount(1L);
        orderEntity.setOrderID(1L);
        orderEntity.setCustomerID(1L);
        orderEntity.setBookID(1L);

        orderRepository.save(orderEntity);

        OrderResponseDto savedOrder = orderService.getOrderDetails(orderEntity.getOrderID());

        Assert.assertTrue(savedOrder.getOrderID() == orderEntity.getOrderID());
    }

    @Test
    @Transactional
    public void stockUpdateTest() {
        createBookHelper();
        long quantity = bookEntity.getQuantity();

        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrderAmount(1L);
        orderRequestDto.setOrderID(1L);
        orderRequestDto.setCustomerID(1L);
        orderRequestDto.setBookID(1L);

        orderService.updateStock(orderRequestDto);

        Assert.assertTrue(quantity == (bookRepository.getByBookID(1L).getQuantity() + orderRequestDto.getOrderAmount()));
    }

    public void createBookHelper(){
        bookEntity = new BookEntity();
        bookEntity.setSales(5L);
        bookEntity.setTitle("Aşkı memnu");
        bookEntity.setAuthor("Halid Ziya Uşaklıgil");
        bookEntity.setPrice((float) 69.9);
        bookEntity.setBookID(1L);
        bookEntity.setCategory("Klasik");
        bookEntity.setPublisher("Epsilon");
        bookEntity.setQuantity(6L);
        bookRepository.save(bookEntity);
    }

    public void createCustomerHelper(){
        customerEntity = new CustomerEntity();
        customerEntity.setAddress("ankara");
        customerEntity.setPhoneNumber("05437369008");
        customerEntity.setName("rabia");
        customerEntity.setId(1L);
        customerEntity.setEmail("rabiabilgucu9778@gmail.com");
        customerEntity.setSurname("bilgucu");

        customerRepository.save(customerEntity);
    }

}
