package com.foxminded.service;

import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Course;
import com.foxminded.model.ScheduleItem;
import com.foxminded.model.Teacher;

import java.util.List;


public interface TeacherService {

    Teacher createTeacher(String firstName, String lastName);

    void updateTeacher(Teacher teacher) throws EntityNotFoundException;

    void deleteTeacher(Teacher teacher) throws EntityNotFoundException;

    List<Teacher> findAll();

    Teacher findTeacherById(long id) throws EntityNotFoundException;

    void assignSchedule(ScheduleItem scheduleItem, Teacher teacher) throws EntityNotFoundException;
}
