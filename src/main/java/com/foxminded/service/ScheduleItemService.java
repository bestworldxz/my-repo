package com.foxminded.service;

import com.foxminded.exception.DatabaseException;
import com.foxminded.model.Course;
import com.foxminded.model.Group;
import com.foxminded.model.ScheduleItem;
import com.foxminded.model.Teacher;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleItemService {

    ScheduleItem createScheduleItem(LocalDate localDate, Group group, Teacher teacher, Course course);

    void update(ScheduleItem scheduleItem);

    List<ScheduleItem> findAll() throws DatabaseException;

    List<ScheduleItem> findMonthSchedule(Group group);

    List<ScheduleItem> findDayScheduleForGroup(LocalDate localDate, Group group) throws DatabaseException;

    List<ScheduleItem> findDaySchedule(LocalDate localDate) throws DatabaseException;

}
