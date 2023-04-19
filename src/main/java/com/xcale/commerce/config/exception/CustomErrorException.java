package com.xcale.commerce.config.exception;

public class CustomErrorException extends RuntimeException {
    public CustomErrorException(String message) {
        super(message);
    }
}