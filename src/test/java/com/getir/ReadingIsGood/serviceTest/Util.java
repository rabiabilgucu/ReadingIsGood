package com.getir.ReadingIsGood.serviceTest;

import com.getir.ReadingIsGood.dto.request.BookRequestDto;
import com.getir.ReadingIsGood.dto.request.CustomerRequestDto;
import com.getir.ReadingIsGood.dto.request.OrderRequestDto;
import com.getir.ReadingIsGood.dto.response.BookResponseDto;
import com.getir.ReadingIsGood.dto.response.CustomerResponseDto;
import com.getir.ReadingIsGood.dto.response.OrderResponseDto;
import com.getir.ReadingIsGood.entity.BookEntity;
import com.getir.ReadingIsGood.entity.CustomerEntity;
import com.getir.ReadingIsGood.entity.OrderEntity;
import com.getir.ReadingIsGood.exception.ErrorType;
import com.getir.ReadingIsGood.exception.GlobalException;
import com.getir.ReadingIsGood.repository.BookRepository;
import com.getir.ReadingIsGood.repository.CustomerRepository;
import com.getir.ReadingIsGood.repository.OrderRepository;
import com.getir.ReadingIsGood.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Util {
    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    private final BookService bookService;

    public CustomerResponseDto createCustomer(){
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setAddress("ankara");
        customerRequestDto.setPhoneNumber("05437369008");
        customerRequestDto.setName("rabia");
        customerRequestDto.setId(1L);
        customerRequestDto.setEmail("rabiabilgucu9778@gmail.com");
        customerRequestDto.setSurname("bilgucu");
        CustomerEntity customerEntity = modelMapper.map(customerRequestDto, CustomerEntity.class);

        if (customerAlreadyExist(customerEntity)) {
            throw new GlobalException(ErrorType.CUSTOMER_ALREADY_EXIST_ERROR);
        }
        customerEntity.setCreatedDate(LocalDateTime.now());
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return CustomerResponseDto.builder()
                .name(savedCustomer.getName())
                .surname(savedCustomer.getSurname())
                .build();
    }


    public void createBook(){
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setSales(5L);
        bookRequestDto.setTitle("Aşkı memnu");
        bookRequestDto.setAuthor("Halid Ziya Uşaklıgil");
        bookRequestDto.setPrice((float) 69.9);
        bookRequestDto.setBookID(1L);
        bookRequestDto.setCategory("Klasik");
        bookRequestDto.setPublisher("Epsilon");
        bookRequestDto.setQuantity(1L);
        BookEntity bookEntity = modelMapper.map(bookRequestDto, BookEntity.class);

        BookEntity savedBook ;

        if (checkBookExists(bookEntity)) {
            bookEntity.setQuantity(getBookQuantity(bookEntity) + bookEntity.getQuantity());
            updateQuantity(bookEntity);
            savedBook = bookEntity;
        } else {
            bookEntity.setCreatedDate(LocalDateTime.now());
            savedBook = bookRepository.save(bookEntity);
            savedBook.setQuantity(bookEntity.getQuantity());
        }

        BookResponseDto.builder()
                .title(savedBook.getTitle())
                .author(savedBook.getCategory())
                .price(savedBook.getPrice())
                .category(savedBook.getCategory())
                .build();
    }

    public void createOrder(){
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setOrderAmount(5L);
        orderRequestDto.setOrderID(1L);
        orderRequestDto.setCustomerID(1L);
        orderRequestDto.setBookID(1L);

        OrderEntity orderEntity = modelMapper.map(orderRequestDto, OrderEntity.class);

        if(!customerIsExist(orderEntity)){
            throw new GlobalException(ErrorType.CUSTOMER_IS_NOT_EXIST_ERROR);
        }
        if(!bookIsExist(orderEntity)){
            throw new GlobalException(ErrorType.BOOK_IS_NOT_EXIST);
        }

        if(!bookAmountIsSufficient(orderEntity)){
            throw new GlobalException(ErrorType.BOOK_AMOUNT_IS_NOT_SUFFICIENT_ERROR);
        }

        if(orderEntity.getOrderAmount() <= 0){
            throw new GlobalException(ErrorType.ORDER_AMOUNT_IS_NOT_SUITABLE_ERROR);
        }

        orderEntity.setCreatedDate(LocalDateTime.now());
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        updateBookStock(orderEntity);

        OrderResponseDto.builder()
                .orderID(savedOrder.getBookID())
                .orderAmount(savedOrder.getOrderAmount())
                .customerID(savedOrder.getCustomerID())
                .BookID(savedOrder.getBookID())
                .build();
    }
    private boolean checkBookExists(BookEntity bookEntity){
        return bookRepository.existsByTitle(bookEntity.getTitle());
    }

    public long getBookQuantity(BookEntity bookEntity){
        BookEntity book =  bookRepository.getByTitle(bookEntity.getTitle());
        return book.getQuantity();
    }

    public int updateQuantity(BookEntity bookEntity){
        return bookRepository.updateQuantity(bookEntity.getTitle(), bookEntity.getQuantity());
    }

    private boolean customerAlreadyExist(CustomerEntity customerEntity) {
        return customerRepository.existsByEmail(customerEntity.getEmail()) || customerRepository.existsByPhoneNumber(customerEntity.getPhoneNumber()) ;
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

    private int updateBookStock(OrderEntity orderEntity){
        BookEntity bookEntity = bookRepository.getByBookID(orderEntity.getBookID());
        if(bookService.getBookQuantity(bookEntity) <= 0){
            throw new GlobalException(ErrorType.BOOK_IS_NOT_AVAILABLE_ERROR);
        }
        bookEntity.setQuantity(bookService.getBookQuantity(bookEntity) - orderEntity.getOrderAmount());
        bookService.updateQuantity(bookEntity);
        return 0;
    }

}
