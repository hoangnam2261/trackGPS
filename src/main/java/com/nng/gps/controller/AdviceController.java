package com.nng.gps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> fileValidationError(Exception e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}
