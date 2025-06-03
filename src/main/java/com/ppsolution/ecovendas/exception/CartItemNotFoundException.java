package com.ppsolution.ecovendas.exception;

public class CartItemNotFoundException extends NotFoundException{

    public CartItemNotFoundException() {
        super("Cart item not found");
    }

    public CartItemNotFoundException(String message) {
        super(message);
    }
}
