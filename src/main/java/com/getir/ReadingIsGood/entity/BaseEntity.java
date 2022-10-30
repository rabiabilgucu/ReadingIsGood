package com.getir.ReadingIsGood.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity implements Serializable {
    private LocalDateTime createdDate;

}
