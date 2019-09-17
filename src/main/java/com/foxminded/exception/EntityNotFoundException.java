package com.foxminded.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, Long id) {
        super(clazz.getSimpleName() + " not found id = {" + id + "}");
    }
}
