package com.getir.ReadingIsGood.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrderRequestDto {

    @NotBlank(message = "Order id can not be blank")
    private Long orderID;

    @NotBlank(message = "Customer id can not be blank")
    private Long customerID;

    @NotBlank(message = "Book id can not be blank")
    private Long BookID;

    @NotBlank(message = "Order amount can not be blank")
    private Long orderAmount;
}
