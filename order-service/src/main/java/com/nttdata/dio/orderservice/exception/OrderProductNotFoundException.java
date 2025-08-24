package com.nttdata.dio.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

public class OrderProductNotFoundException extends RuntimeException implements ErrorResponse {
    private final String errorMessage;

    public OrderProductNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }


    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public ProblemDetail getBody() {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(getStatusCode(), getMessage());
        problemDetail.setTitle("Product not found");
        return problemDetail;
    }
}
