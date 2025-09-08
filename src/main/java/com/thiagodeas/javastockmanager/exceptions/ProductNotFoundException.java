package com.thiagodeas.javastockmanager.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Produto não encontrado.");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
    
}
