package com.foxminded.service.impl;

import com.foxminded.dao.ScheduleItemDao;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.ClassRoom;
import com.foxminded.model.ScheduleItem;
import com.foxminded.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl implements UniversityService {

    private static final Logger logger = LoggerFactory.getLogger(UniversityServiceImpl.class);

    @Autowired
    private ScheduleItemDao scheduleItemDao;

    @Override
    public void createSchedule(ScheduleItem scheduleItem, ClassRoom classRoom) throws EntityNotFoundException {
        if (scheduleItem == null) {
            throw new EntityNotFoundException(ScheduleItem.class, scheduleItem.getId());
        }
        if (classRoom == null) {
            throw new EntityNotFoundException(ClassRoom.class, classRoom.getClassRoomId());
        }
        scheduleItem.setClassRoom(classRoom);
        logger.debug("Class room {} added to schedule.", classRoom.getClassRoom());
        scheduleItemDao.save(scheduleItem);
    }
}
