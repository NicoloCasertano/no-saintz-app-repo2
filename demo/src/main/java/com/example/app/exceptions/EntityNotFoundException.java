package com.example.app.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Class<?> entityClass) {
        super(String.format("Couldn't find any entity %S", entityClass));
    }
}
