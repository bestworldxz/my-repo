package com.foxminded.service.impl;

import com.foxminded.dao.TeacherDao;
import com.foxminded.model.Course;
import com.foxminded.model.ScheduleItem;
import com.foxminded.model.Teacher;
import com.foxminded.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher createTeacher(String firstName, String lastName) {
        if (firstName.equals("") || lastName.equals("")) {
            throw new IllegalArgumentException("Teacher name cannot be empty.");
        }
        Teacher teacher = new Teacher(firstName, lastName);
        teacherDao.save(teacher);
        logger.debug("Teacher {} {} created.", firstName, lastName);
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new NullPointerException("Teacher cannot be null.");
        }
        teacherDao.save(teacher);
        logger.debug("Teacher {} {} updated.", teacher.getFirstName(), teacher.getLastName());
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new NullPointerException("Teacher cannot be null.");
        }
        teacherDao.delete(teacher);
        logger.debug("Teacher {} {} deleted.", teacher.getFirstName(), teacher.getLastName());
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public Teacher findTeacherById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID cannot be zero or negative.");
        }
        Teacher teacher = teacherDao.findTeacherByTeacherId(id);
        if (teacher == null) {
            throw new NullPointerException("Teacher not found.");
        }
        logger.debug("Teacher {} {} founded.", teacher.getFirstName(), teacher.getLastName());
        return teacher;
    }

    @Override
    public void assignSchedule(ScheduleItem scheduleItem, Teacher teacher) {
        if (scheduleItem == null || teacher == null) {
            throw new NullPointerException("Object cannot be null");
        }
        teacher.getScheduleItems().add(scheduleItem);
        teacherDao.save(teacher);
        logger.debug("Schedule: group - {}, course - {}, auditory - {} assigned to teacher {} {}.",
                scheduleItem.getGroup().getGroupName(), scheduleItem.getCourse().getCourseName(),
                scheduleItem.getClassRoom().getClassRoom(), teacher.getFirstName(), teacher.getLastName());
    }

}
