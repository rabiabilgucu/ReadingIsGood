package com.getir.ReadingIsGood.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GlobalException extends RuntimeException {

    private final ErrorType errorCode;

    public GlobalException(ErrorType errorCode) {
        super();
        this.errorCode = errorCode;
    }

}