package com.foxminded.exception;

import org.springframework.util.StringUtils;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Class clazz, Long id) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), id));
    }

    private static String generateMessage(String entity, Long id) {
        return StringUtils.capitalize(entity) +
                " was not found for parameters " + "id={" +
                id + "}";
    }

}
