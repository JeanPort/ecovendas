package com.ppsolution.ecovendas.exception;

public class CategoryNotFoundException extends NotFoundException{

    public CategoryNotFoundException() {
        super("Category not found");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
