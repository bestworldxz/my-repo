package com.foxminded.exception;

import java.sql.SQLException;

public class DatabaseException extends SQLException {

    public DatabaseException(Class clazz){
        super(clazz.getSimpleName() + " not found in database.");
    }
}
