package com.getir.ReadingIsGood.repository;

import com.getir.ReadingIsGood.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByName(String name);

    CustomerEntity findById(long id);
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsById(Long customerId);


}
