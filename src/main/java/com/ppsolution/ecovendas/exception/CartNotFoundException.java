package com.ppsolution.ecovendas.exception;

public class CartNotFoundException extends NotFoundException{

    public CartNotFoundException() {
        super("Cart not found");
    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
