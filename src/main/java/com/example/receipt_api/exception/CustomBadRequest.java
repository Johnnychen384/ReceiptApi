package com.example.receipt_api.exception;

import org.springframework.http.HttpStatus;

public class CustomBadRequest extends RuntimeException {

    private final HttpStatus status;

    public CustomBadRequest(String msg) {
        super(msg);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public CustomBadRequest(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
