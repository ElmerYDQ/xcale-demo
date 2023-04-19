package com.xcale.commerce.config.exception;

public class HttpFailedException extends RuntimeException {
    public HttpFailedException(String message) {
        super(message);
    }
}