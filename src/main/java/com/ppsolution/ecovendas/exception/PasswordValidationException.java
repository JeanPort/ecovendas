package com.ppsolution.ecovendas.exception;

public class PasswordValidationException extends RuntimeException{
    public PasswordValidationException() {
        super("Password invalido");
    }

    public PasswordValidationException(String message) {
        super(message);
    }
}
