package com.ostrikova.blogserver;

import org.springframework.http.HttpStatus;

public class AppException extends Exception {

    private String message = null;
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public AppException(String message) {
        super(message);
        this.message = message;
    }

    public AppException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
