package com.foxminded.service.impl;

import com.foxminded.dao.ClassRoomDao;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.ClassRoom;
import com.foxminded.service.ClassRoomService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomDao classRoomDao;

    @Override
    public ClassRoom createClassRoom(int numberOfClassRoom) {
        if (numberOfClassRoom <= 0) {
            throw new IllegalArgumentException("Class room cannot be zero or negative.");
        }
        ClassRoom classRoom = new ClassRoom(numberOfClassRoom);
        classRoomDao.save(classRoom);
        log.debug("Class room {} created.", numberOfClassRoom);
        return classRoom;
    }

    @Override
    public List<ClassRoom> findAll() {
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
