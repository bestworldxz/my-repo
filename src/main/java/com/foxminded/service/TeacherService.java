package com.foxminded.service;

import com.foxminded.model.Course;
import com.foxminded.model.ScheduleItem;
import com.foxminded.model.Teacher;

import java.util.List;


public interface TeacherService {

    Teacher createTeacher(String firstName, String lastName);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    List<Teacher> findAll();

    Teacher findTeacherById(long id);

    void assignSchedule(ScheduleItem scheduleItem, Teacher teacher);
}
