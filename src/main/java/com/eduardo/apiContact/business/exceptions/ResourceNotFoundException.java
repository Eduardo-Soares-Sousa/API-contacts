package com.eduardo.apiContact.business.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String messagem, Throwable throwable) {
        super(messagem, throwable);
    }
}
