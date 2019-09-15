package com.foxminded.exception;

import org.springframework.util.StringUtils;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Class clazz, Long id) {
        super(EntityNotFoundException.entityNotFound(clazz.getSimpleName(), id));
    }

    public EntityNotFoundException(Class clazz, String groupName) {
        super(EntityNotFoundException.entityNotFound(clazz.getSimpleName(), groupName));
    }

    private static String entityNotFound(String entity, Long id) {
        return StringUtils.capitalize(entity) +
                " was not found for parameters " + "id={" +
                id + "}";
    }

    private static String entityNotFound(String entity, String groupName) {
        return "Cannot create " + StringUtils.capitalize(entity) +
                " with group name={" + groupName + "}";
    }

}
