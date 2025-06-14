package com.js.java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGenericException(Exception ex){
        Map<String,Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", ex.getMessage());
        error.put(("status"), HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleTaskNotFoundException(TaskNotFoundException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("time_stamp", System.currentTimeMillis());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
