package com.ppsolution.ecovendas.exception;

public class ProductNotFoundException extends NotFoundException{

    public ProductNotFoundException() {
        super("Product not found");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
