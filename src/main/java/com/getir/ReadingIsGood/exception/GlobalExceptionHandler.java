package com.getir.ReadingIsGood.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(GlobalException.class)
        public final ResponseEntity<GlobalException> handleCustomerApiException(GlobalException ex, WebRequest request) {
            return prepareResponse(ex);
        }

        @ExceptionHandler(Exception.class)
        public final ResponseEntity<Exception> handleException(
                Exception ex) {
            return ResponseEntity.status(ErrorType.INTERNAL_SERVER_ERROR.getHttpStatusCode()).body(ex);
        }

        private static ResponseEntity<GlobalException> prepareResponse(GlobalException exception) {
            return ResponseEntity.status(exception.getErrorCode().getHttpStatusCode()).body(exception);
        }
    }

