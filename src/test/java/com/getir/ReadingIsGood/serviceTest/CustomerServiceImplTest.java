package com.getir.ReadingIsGood.serviceTest;

import com.getir.ReadingIsGood.ReadingIsGoodApplication;
import com.getir.ReadingIsGood.dto.request.CustomerRequestDto;
import com.getir.ReadingIsGood.entity.CustomerEntity;
import com.getir.ReadingIsGood.repository.CustomerRepository;
import com.getir.ReadingIsGood.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest(classes = ReadingIsGoodApplication.class)
@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    @Transactional
    public void createCustomerTest() {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setAddress("ankara");
        customerRequestDto.setPhoneNumber("05437369008");
        customerRequestDto.setName("rabia");
        customerRequestDto.setId(1L);
        customerRequestDto.setEmail("rabiabilgucu9778@gmail.com");
        customerRequestDto.setSurname("bilgucu");

        customerService.createCustomer(customerRequestDto);

        CustomerEntity customerEntity = customerRepository.findById(customerRequestDto.getId());

        Assert.assertTrue(customerEntity.getId() == customerRequestDto.getId()
                && customerEntity.getPhoneNumber() == customerRequestDto.getPhoneNumber()
                && customerEntity.getEmail() == customerRequestDto.getEmail()
                && customerEntity.getSurname() == customerRequestDto.getSurname()
                && customerEntity.getName() == customerRequestDto.getName()
                && customerEntity.getAddress() == customerRequestDto.getAddress());

    }
}