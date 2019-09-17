package com.foxminded.exception;

public class WrongArgumentException extends RuntimeException {

    public WrongArgumentException(Class clazz, String groupName) {
        super("Cannot create " + clazz.getSimpleName() + " with name={" + groupName + "}");
    }

    public WrongArgumentException(Class clazz, String firstName, String lastName) {
        super("Cannot create " + clazz.getSimpleName() +
                " with first name={" + firstName + "}"
                + " last name={" + lastName + "}");
    }
}
