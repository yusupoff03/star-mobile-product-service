package com.example.sofiyaproductservice.exception;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public class RequestValidationException extends RuntimeException{

    String message;

    public RequestValidationException(List<ObjectError> allErrors) {
        StringBuilder errorMessage = new StringBuilder();
        for (ObjectError allError : allErrors) {
            errorMessage.append(allError.getDefaultMessage()).append("\n");

        }
        this.message = errorMessage.toString();
    }

    public String getMessage() {
        return message;
    }
}
