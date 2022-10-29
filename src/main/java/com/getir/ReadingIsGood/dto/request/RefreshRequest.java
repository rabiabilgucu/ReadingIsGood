package com.getir.ReadingIsGood.dto.request;


import lombok.Data;

@Data
public class RefreshRequest {

    Long userId;
    String refreshToken;
}