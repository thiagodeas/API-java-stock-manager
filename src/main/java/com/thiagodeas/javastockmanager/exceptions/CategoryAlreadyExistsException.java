package com.thiagodeas.javastockmanager.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super("Essa categoria já existe.");
    }
}
