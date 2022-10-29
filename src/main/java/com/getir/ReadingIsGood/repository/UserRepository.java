package com.getir.ReadingIsGood.repository;

import com.getir.ReadingIsGood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

}