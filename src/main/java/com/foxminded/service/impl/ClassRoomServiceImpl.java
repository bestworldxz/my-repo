package com.foxminded.service.impl;

import com.foxminded.dao.ClassRoomDao;
import com.foxminded.model.ClassRoom;
import com.foxminded.service.ClassRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    private static final Logger logger = LoggerFactory.getLogger(ClassRoomServiceImpl.class);

    @Autowired
    private ClassRoomDao classRoomDao;

    @Override
    public ClassRoom createClassRoom(int numberOfClassRoom) {
        if (numberOfClassRoom <= 0) {
            throw new IllegalArgumentException("Class room cannot be zero or negative.");
        }
        ClassRoom classRoom = new ClassRoom(numberOfClassRoom);
        classRoomDao.save(classRoom);
        logger.debug("Class room {} created.", numberOfClassRoom);
        return classRoom;
    }

    @Override
    public List<ClassRoom> findAll() {
        return classRoomDao.findAll();
    }

    @Override
    public void deleteClassRoom(ClassRoom classRoom) {
        if (classRoom == null) {
            throw new NullPointerException("Class room cannot be null.");
        }
        classRoomDao.delete(classRoom);
        logger.debug("Class room {} deleted.", classRoom.getClassRoom());
    }
}
