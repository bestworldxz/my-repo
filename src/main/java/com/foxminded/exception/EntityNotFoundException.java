package com.foxminded.exception;

import java.time.LocalDate;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, Long id) {
        super(clazz.getSimpleName() + " not found id = {" + id + "}");
    }

    public EntityNotFoundException(Class clazz, LocalDate date) {
        super(clazz.getSimpleName() + " not found with date ={" + date + "}");
    }
}
