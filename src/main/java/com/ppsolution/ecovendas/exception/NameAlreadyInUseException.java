package com.ppsolution.ecovendas.exception;

public class NameAlreadyInUseException extends ResourceAlreadyInUseException{

    public NameAlreadyInUseException() {
        super("Name already in use");
    }

    public NameAlreadyInUseException(String message) {
        super(message);
    }
}
