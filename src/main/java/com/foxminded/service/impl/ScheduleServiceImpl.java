package com.foxminded.service.impl;

import com.foxminded.dao.ScheduleItemDao;
import com.foxminded.exception.DatabaseException;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.exception.WrongArgumentException;
import com.foxminded.model.Course;
import com.foxminded.model.Group;
import com.foxminded.model.ScheduleItem;
import com.foxminded.model.Teacher;
import com.foxminded.service.ScheduleItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleItemService {

    @Autowired
    private ScheduleItemDao scheduleItemDao;

    @Override
    public ScheduleItem createScheduleItem(LocalDate localDate, Group group, Teacher teacher, Course course)
            throws EntityNotFoundException {
        if (localDate == null) {
            throw new EntityNotFoundException(LocalDate.class, localDate);
        }
        if (group == null) {
            throw new EntityNotFoundException(Group.class, group.getGroupId());
        }
        if (teacher == null) {
            throw new EntityNotFoundException(Teacher.class, teacher.getTeacherId());
        }
        if (course == null) {
            throw new EntityNotFoundException(Course.class, course.getCourseId());
        }
        ScheduleItem scheduleItem = new ScheduleItem(localDate, group, teacher, course);
        log.debug("Schedule item: date - {}, group - {}, teacher - {} {}, course - {} created.",
                localDate, group.getGroupName(), teacher.getFirstName(), teacher.getLastName(), course.getCourseName());
        scheduleItemDao.save(scheduleItem);
        return scheduleItem;
    }

    @Override
    public void update(ScheduleItem scheduleItem) throws EntityNotFoundException {
        if (scheduleItem == null) {
            throw new EntityNotFoundException(ScheduleItem.class, scheduleItem.getId());
        }
        scheduleItemDao.save(scheduleItem);
        log.debug("Schedule item: date - {}, group - {}, teacher - {} {}, course - {} updated.",
                scheduleItem.getDate(), scheduleItem.getGroup().getGroupName(),
                scheduleItem.getTeacher().getFirstName(), scheduleItem.getTeacher().getLastName(),
                scheduleItem.getCourse().getCourseName());
    }

    @Override
    public List<ScheduleItem> findAll() throws DatabaseException {
        List<ScheduleItem> scheduleItems = scheduleItemDao.findAll();
        if (scheduleItems.isEmpty()) {
            throw new DatabaseException(ScheduleItem.class);
        }
        return scheduleItemDao.findAll();
    }

    @Override
    public List<ScheduleItem> findMonthSchedule(Group group) throws EntityNotFoundException {
        if (group == null) {
            throw new EntityNotFoundException(Group.class, group.getGroupId());
        }
        return scheduleItemDao.findByGroup(group);
    }

    @Override
    public List<ScheduleItem> findDayScheduleForGroup(LocalDate localDate, Group group)
            throws WrongArgumentException, EntityNotFoundException, DatabaseException {
        if (localDate == null) {
            throw new WrongArgumentException(LocalDate.class, localDate);
        }
        if (group == null) {
            throw new EntityNotFoundException(Group.class, group.getGroupId());
        }
        List<ScheduleItem> scheduleItems = scheduleItemDao.findByGroupAndDate(group, localDate);
        if (scheduleItems.isEmpty()) {
            throw new DatabaseException(ScheduleItem.class);
        }
        return scheduleItems;
    }

    @Override
    public List<ScheduleItem> findDaySchedule(LocalDate localDate)
            throws WrongArgumentException, DatabaseException {
        if (localDate == null) {
            throw new WrongArgumentException(LocalDate.class, localDate);
        }
        List<ScheduleItem> scheduleItems = scheduleItemDao.findByDate(localDate);
        if (scheduleItems.isEmpty()) {
            throw new DatabaseException(ScheduleItem.class);
        }
        return scheduleItems;
    }
}
