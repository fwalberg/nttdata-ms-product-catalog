package com.nttdata.dio.productservice.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFound(ProductNotFoundException ex) {
        return ex.getBody();
    }

    @ExceptionHandler(ProductInvalidArgumentException.class)
    public ProblemDetail handleProductInvalidArgument(ProductInvalidArgumentException ex) {
        return ex.getBody();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Invalid request");
        return problemDetail;
    }

    @ExceptionHandler({DataIntegrityViolationException.class, JpaSystemException.class})
    public ProblemDetail handleTechnicalException(Exception ex) {
        ProblemDetail problem =
                ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        problem.setTitle("Internal Server Error");
        problem.setProperty("errorMessage", ex.getMessage());
        return problem;
    }
}