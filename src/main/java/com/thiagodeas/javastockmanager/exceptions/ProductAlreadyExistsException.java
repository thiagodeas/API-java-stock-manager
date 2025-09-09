package com.thiagodeas.javastockmanager.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {
        super("Esse produto já existe.");
    }

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
