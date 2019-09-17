package com.foxminded.service.impl;

import com.foxminded.dao.ScheduleItemDao;
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
    public ScheduleItem createScheduleItem(LocalDate localDate, Group group, Teacher teacher, Course course) {
        if (localDate == null || group == null || teacher == null || course == null) {
            throw new NullPointerException("Object cannot be null.");
        }
        ScheduleItem scheduleItem = new ScheduleItem(localDate, group, teacher, course);
        log.debug("Schedule item: date - {}, group - {}, teacher - {} {}, course - {} created.",
                localDate, group.getGroupName(), teacher.getFirstName(), teacher.getLastName(), course.getCourseName());
        scheduleItemDao.save(scheduleItem);
        return scheduleItem;
    }

    @Override
    public void update(ScheduleItem scheduleItem){
//        if (scheduleItem == null) {
//            throw new EntityNotFoundException(ScheduleItem.class, scheduleItem.getId());
//        }
        scheduleItemDao.save(scheduleItem);
        log.debug("Schedule item: date - {}, group - {}, teacher - {} {}, course - {} updated.",
                scheduleItem.getDate(), scheduleItem.getGroup().getGroupName(),
                scheduleItem.getTeacher().getFirstName(), scheduleItem.getTeacher().getLastName(),
                scheduleItem.getCourse().getCourseName());
    }

    @Override
    public List<ScheduleItem> findAll() {
        return scheduleItemDao.findAll();
    }

    @Override
    public List<ScheduleItem> findMonthSchedule(Group group){
//        if (group == null) {
//            throw new EntityNotFoundException(Group.class, group.getGroupId());
//        }
        return scheduleItemDao.findByGroup(group);
    }

    @Override
    public List<ScheduleItem> findDayScheduleForGroup(LocalDate localDate, Group group) {
        if (localDate == null || group == null) {
            throw new NullPointerException("Object cannot be null.");
        }
        return scheduleItemDao.findByGroupAndDate(group, localDate);
    }

    @Override
    public List<ScheduleItem> findDaySchedule(LocalDate localDate){
//        if (localDate == null) {
//            throw new EntityNotFoundException(ScheduleItem.class, localDate);
//        }
        return scheduleItemDao.findByDate(localDate);
    }
}
