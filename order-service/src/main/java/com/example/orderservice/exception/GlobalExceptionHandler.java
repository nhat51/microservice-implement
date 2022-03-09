package com.example.orderservice.exception;

import com.example.orderservice.response.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(BindException e){
        Map<String,String> errorDetail = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) ->{
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errorDetail.put(fieldName, message);
                }
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseApi(HttpStatus.BAD_REQUEST,"400",errorDetail));
    }

  /*  @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<?> handleNullPointerException(NullPointerException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseApi(HttpStatus.BAD_REQUEST,e.getMessage(),"")
        );
    }*/
}
