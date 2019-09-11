package com.foxminded.service;

import com.foxminded.model.Course;
import com.foxminded.model.Group;
import com.foxminded.model.ScheduleItem;
import com.foxminded.model.Teacher;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleItemService {

    ScheduleItem createScheduleItem(LocalDate localDate, Group group, Teacher teacher, Course course);

    void update(ScheduleItem scheduleItem);

    List<ScheduleItem> findAll();

    List<ScheduleItem> findMonthSchedule(Group group);

    List<ScheduleItem> findDaySchedule(LocalDate localDate, Group group);

}
