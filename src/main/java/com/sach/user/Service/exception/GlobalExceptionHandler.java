package com.sach.user.Service.exception;

import com.sach.user.Service.payloads.ApiResourse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiResourse> handleResoureNotFoundException(ResourseNotFoundException ex){
     String Message=ex.getMessage();

    ApiResourse resourse= ApiResourse.builder().Message(Message).sucess(true).status(HttpStatus.NOT_FOUND).build();

    return new ResponseEntity<>(resourse,HttpStatus.NOT_FOUND);
    }
}
