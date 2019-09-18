package com.foxminded.service.impl;

import com.foxminded.dao.ClassRoomDao;
import com.foxminded.exception.DatabaseException;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.exception.WrongArgumentException;
import com.foxminded.model.ClassRoom;
import com.foxminded.service.ClassRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomDao classRoomDao;

    @Override
    public ClassRoom createClassRoom(Integer numberOfClassRoom) throws WrongArgumentException {
        if (numberOfClassRoom <= 0 || numberOfClassRoom == null) {
            throw new WrongArgumentException(ClassRoom.class, numberOfClassRoom);
        }
        ClassRoom classRoom = new ClassRoom(numberOfClassRoom);
        classRoomDao.save(classRoom);
        log.debug("Class room {} created.", numberOfClassRoom);
        return classRoom;
    }

    @Override
    public List<ClassRoom> findAll() throws DatabaseException {
        List<ClassRoom> classRooms = classRoomDao.findAll();
        if (classRooms.isEmpty()) {
            throw new DatabaseException(ClassRoom.class);
        }
        return classRoomDao.findAll();
    }

    @Override
    public void deleteClassRoom(ClassRoom classRoom) throws EntityNotFoundException {
        if (classRoom == null) {
            throw new EntityNotFoundException(ClassRoom.class, classRoom.getClassRoomId());
        }
        classRoomDao.delete(classRoom);
        log.debug("Class room {} deleted.", classRoom.getClassRoom());
    }
}
