package com.example.sofiyaproductservice.config;

import com.example.sofiyaproductservice.exception.AuthenticationFailedException;
import com.example.sofiyaproductservice.exception.DataNotFoundException;
import com.example.sofiyaproductservice.exception.RequestValidationException;
import com.example.sofiyaproductservice.exception.UnauthorizedAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<String> dataNotFoundExceptionHandler(
            DataNotFoundException e){
        return ResponseEntity.status(401).body(e.getMessage());
    }



    @ExceptionHandler(value = {AuthenticationFailedException.class})
    public ResponseEntity<String> authenticationFailedException(
            AuthenticationFailedException e){
        return ResponseEntity.status(401).body(e.getMessage());
    }


    @ExceptionHandler(value = {RequestValidationException.class})
    public ResponseEntity<String> requestValidationException(
            RequestValidationException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
@ExceptionHandler(value = {UnauthorizedAccessException.class})
    public ResponseEntity<String> unauthorizedAccessException
        (UnauthorizedAccessException e){
        return ResponseEntity.status(403).body(e.getMessage());
}



}
