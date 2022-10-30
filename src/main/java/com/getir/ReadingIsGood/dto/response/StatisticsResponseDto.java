package com.getir.ReadingIsGood.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StatisticsResponseDto {
    private String month;
    private int totalOrder;
    private int totalBook;
    private float totalPrice;
}
