package com.example.apicaixaeletronico.exception;

public class SaqueException extends RuntimeException {

    private String message;

    public SaqueException(String message) {
        super(message);
    }
}

