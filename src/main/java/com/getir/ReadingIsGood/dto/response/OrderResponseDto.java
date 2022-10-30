package com.getir.ReadingIsGood.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class OrderResponseDto {
    private Long orderID;

    private Long customerID;

    private Long BookID;

    private Long orderAmount;

    private Long creationDate;
}
