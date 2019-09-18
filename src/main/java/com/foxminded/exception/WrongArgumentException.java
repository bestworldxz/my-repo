package com.foxminded.exception;

import java.time.LocalDate;

public class WrongArgumentException extends RuntimeException {

    public WrongArgumentException(Class clazz, String name) {
        super("Cannot create " + clazz.getSimpleName() + " with name={" + name + "}");
    }

    public WrongArgumentException(Class clazz, String firstName, String lastName) {
        super("Cannot create " + clazz.getSimpleName() +
                " with first name={" + firstName + "}"
                + " last name={" + lastName + "}");
    }

    public WrongArgumentException(Class clazz, Integer number) {
        super("Cannot create " + clazz.getSimpleName() + " with number={" + number + "}");
    }

    public WrongArgumentException(Class clazz, LocalDate localDate) {
        super("Cannot use " + clazz.getSimpleName() + " with date={" + localDate + "}");
    }
}
