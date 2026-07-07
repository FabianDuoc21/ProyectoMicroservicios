package com.example.comuna.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}