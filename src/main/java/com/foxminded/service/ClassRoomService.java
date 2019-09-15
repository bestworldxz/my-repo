package com.foxminded.service;

import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.ClassRoom;

import java.util.List;

public interface ClassRoomService {

    ClassRoom createClassRoom(int numberOfClassRoom);

    List<ClassRoom> findAll();

    void deleteClassRoom(ClassRoom classRoom) throws EntityNotFoundException;


}
