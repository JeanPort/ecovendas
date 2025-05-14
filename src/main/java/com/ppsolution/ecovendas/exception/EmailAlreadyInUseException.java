package com.ppsolution.ecovendas.exception;

public class EmailAlreadyInUseException extends ResourceAlreadyInUseException{

    public EmailAlreadyInUseException() {
        super("Email already in use");
    }

    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
