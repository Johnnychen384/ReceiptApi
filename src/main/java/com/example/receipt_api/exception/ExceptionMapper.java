package com.example.receipt_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(CustomBadRequest.class)
    public ResponseEntity<CustomBadRequest> handle(CustomBadRequest ex) {
        return new ResponseEntity<>(ex, ex.getStatus());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handle(NoSuchElementException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Message", ex.getMessage());
        error.put("Error", "Not Found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
