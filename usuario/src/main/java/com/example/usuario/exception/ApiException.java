package com.example.usuario.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
