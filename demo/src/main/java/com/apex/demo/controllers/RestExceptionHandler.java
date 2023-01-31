package com.apex.demo.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.apex.demo.dto.ApiError;
import com.apex.demo.exceptions.OrderNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<ApiError> handleOrderNotFound(OrderNotFoundException ex, WebRequest request) {
        log.info("Order not found: " + ex.getMessage());
        ApiError apiError = new ApiError(new Date(), HttpStatus.NOT_FOUND, "Order not found", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleInternalException(Exception ex, WebRequest request) {
        log.info("Internal exception: " + ex.getMessage());
        ApiError apiError = new ApiError(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
