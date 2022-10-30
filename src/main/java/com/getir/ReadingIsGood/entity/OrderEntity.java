package com.getir.ReadingIsGood.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class OrderEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderID;

    @Column(name = "customerID", nullable = false)
    private Long customerID;

    @Column(name = "bookID", nullable = false)
    private Long BookID;

    @Column(name = "orderAmount", nullable = false)
    private Long orderAmount;

}
