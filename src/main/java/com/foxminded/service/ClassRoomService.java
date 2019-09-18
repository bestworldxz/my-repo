package com.foxminded.service;

import com.foxminded.exception.DatabaseException;
import com.foxminded.model.ClassRoom;

import java.util.List;

public interface ClassRoomService {

    ClassRoom createClassRoom(Integer numberOfClassRoom);

    List<ClassRoom> findAll() throws DatabaseException;

    void deleteClassRoom(ClassRoom classRoom);


}
