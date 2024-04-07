package com.example.springboot.config;

import com.example.springboot.models.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handlerUserNotFound (ProductNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
}
