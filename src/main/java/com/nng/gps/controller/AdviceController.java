package com.nng.gps.controller;

import com.nng.gps.exception.GPXFormatException;
import com.nng.gps.exception.NotExistedTrackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class AdviceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(NotExistedTrackException.class)
    public ResponseEntity<String> nonExistenceTrack(NotExistedTrackException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GPXFormatException.class)
    public ResponseEntity<Object> fileValidationError(GPXFormatException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleRequestParamViolated(ConstraintViolationException  exception) {
        Map<String, Object> response = new HashMap<>();
        List<String> errorMessage = exception.getConstraintViolations()
                .stream()
                .map(field -> field.getPropertyPath().toString() + ":" + field.getMessage())
                .collect(Collectors.toList());
        response.put("message", errorMessage);
        response.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalExceptionHandler(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
