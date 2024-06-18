package com.suspensive.pruebas_unitarias.controllers;


import com.suspensive.pruebas_unitarias.models.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFound(){
        return ResponseEntity.notFound().build();
    }
}
