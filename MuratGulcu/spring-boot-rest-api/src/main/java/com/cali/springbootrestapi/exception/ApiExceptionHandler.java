package com.cali.springbootrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ExceptionResponse> illegalException (Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),exception.getMessage(),"1000");
        return  new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);

    }

    @ExceptionHandler(UserNotFound.class)
    public final ResponseEntity<ExceptionResponse> userNotFoundException (Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),exception.getMessage(),"2000");
        return  new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> exception (Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),exception.getMessage(),"5000");
        return  new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);

    }

}
