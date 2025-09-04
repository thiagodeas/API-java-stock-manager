package com.thiagodeas.javastockmanager.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super("Categoria não encontrada.");
    }
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
