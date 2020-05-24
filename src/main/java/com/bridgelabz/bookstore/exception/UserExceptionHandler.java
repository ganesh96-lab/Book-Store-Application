package com.bridgelabz.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserException.class)
    private ResponseEntity UserExceptionHandler(UserException userException){
        return new ResponseEntity(userException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
