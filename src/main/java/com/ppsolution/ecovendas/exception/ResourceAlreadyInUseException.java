package com.ppsolution.ecovendas.exception;

public class ResourceAlreadyInUseException extends RuntimeException{
    public ResourceAlreadyInUseException() {
        super("Resource already in use");
    }

    public ResourceAlreadyInUseException(String message) {
        super(message);
    }
}
