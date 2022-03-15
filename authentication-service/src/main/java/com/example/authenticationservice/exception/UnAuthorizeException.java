package com.example.authenticationservice.exception;

public class UnAuthorizeException extends RuntimeException{
    public UnAuthorizeException(String message){
        super(message);
    }
}
