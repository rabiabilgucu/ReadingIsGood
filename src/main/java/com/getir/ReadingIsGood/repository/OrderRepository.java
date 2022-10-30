package com.getir.ReadingIsGood.repository;

import com.getir.ReadingIsGood.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    boolean existsByOrderID(long id);

    OrderEntity getByOrderID(long id);

    List<OrderEntity> findAllByCustomerID(long id);
    Page<OrderEntity> findAllByCustomerID(long id, Pageable pageable);

    List<OrderEntity> findAllByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
