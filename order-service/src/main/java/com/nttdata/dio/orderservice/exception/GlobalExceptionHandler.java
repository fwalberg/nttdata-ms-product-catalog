package com.nttdata.dio.orderservice.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderProductNotFoundException.class)
    public ProblemDetail handleProductNotFound(OrderProductNotFoundException ex) {
        return ex.getBody();
    }

    @ExceptionHandler(OrderProductInvalidArgumentException.class)
    public ProblemDetail handleProductInvalidArgument(OrderProductInvalidArgumentException ex) {
        return ex.getBody();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Invalid request");
        return problemDetail;
    }

    @ExceptionHandler(FeignException.class)
    public ProblemDetail handleTechnicalException(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}