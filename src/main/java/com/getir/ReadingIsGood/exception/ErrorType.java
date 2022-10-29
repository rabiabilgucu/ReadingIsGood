package com.getir.ReadingIsGood.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_SERVER_ERROR(1000, new Date(),"Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
    FIELD_VALIDATION_ERROR(1001, new Date(),"Field validation error.", HttpStatus.BAD_REQUEST),
    CUSTOMER_ALREADY_EXIST_ERROR(1002, new Date(),"Customer already exist.", HttpStatus.BAD_REQUEST),
    BOOK_IS_NOT_EXIST(1003, new Date(),"Book is not exist.", HttpStatus.BAD_REQUEST),
    CUSTOMER_IS_NOT_EXIST_ERROR(1004, new Date(),"Customer is not exist.", HttpStatus.BAD_REQUEST),

    BOOK_AMOUNT_IS_NOT_SUFFICIENT_ERROR(1005, new Date(),"Book amount is not sufficient", HttpStatus.BAD_REQUEST),
    ORDER_AMOUNT_IS_NOT_SUITABLE_ERROR(1006, new Date(),"Order amount is not suitable", HttpStatus.BAD_REQUEST),
    ORDER_IS_NOT_EXIST_ERROR(1007, new Date(),"Order amount is not suitable", HttpStatus.BAD_REQUEST),

    BOOK_IS_NOT_AVAILABLE_ERROR(1008, new Date(),"Book is sold out.", HttpStatus.BAD_REQUEST),

    DATE_IS_NOT_VALID_ERROR(1009, new Date(),"Date is not valid.", HttpStatus.BAD_REQUEST);








    private int errorCode;
    private Date errorDate;
    private String errorMessage;
    private HttpStatus httpStatusCode;
}
