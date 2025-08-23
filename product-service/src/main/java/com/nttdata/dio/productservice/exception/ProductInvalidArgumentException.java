package com.nttdata.dio.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

public class ProductInvalidArgumentException extends RuntimeException implements ErrorResponse {
    private final String errorMessage;

    public ProductInvalidArgumentException(String errorMessage) {
        super("Product argument invalide. " + errorMessage);
        this.errorMessage = errorMessage;
    }


    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public ProblemDetail getBody() {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(getStatusCode(), getMessage());
        problemDetail.setTitle("Product argument invalid");
        problemDetail.setProperty("errorMessage", errorMessage);
        return problemDetail;
    }
}
