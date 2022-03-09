package com.example.orderservice.response;

import org.springframework.http.HttpStatus;

public class ResponseApi {
    private String message;
    private HttpStatus status;
    private Object data;

    public ResponseApi() {
    }

    public ResponseApi(HttpStatus status,String message, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
