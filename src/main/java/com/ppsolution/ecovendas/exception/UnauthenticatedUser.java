package com.ppsolution.ecovendas.exception;

public class UnauthenticatedUser extends RuntimeException{
    public UnauthenticatedUser() {
        super("Usuário não autenticado");
    }

    public UnauthenticatedUser(String message) {
        super(message);
    }
}
