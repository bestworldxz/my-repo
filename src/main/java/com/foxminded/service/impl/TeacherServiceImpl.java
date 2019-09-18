package com.foxminded.service.impl;

import com.foxminded.dao.TeacherDao;
import com.foxminded.exception.DatabaseException;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.exception.WrongArgumentException;
import com.foxminded.model.ScheduleItem;
import com.foxminded.model.Teacher;
import com.foxminded.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher createTeacher(String firstName, String lastName) throws WrongArgumentException {
        if (firstName.equals(" ") || firstName.isEmpty() || firstName.matches("[0-9]+")) {
            throw new WrongArgumentException(Teacher.class, firstName, lastName);
        }
        if (lastName.equals(" ") || lastName.isEmpty() || lastName.matches("[0-9]+")) {
            throw new WrongArgumentException(Teacher.class, firstName, lastName);
        }
        Teacher teacher = new Teacher(firstName, lastName);
        teacherDao.save(teacher);
        log.debug("Teacher {} {} created.", firstName, lastName);
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) throws EntityNotFoundException {
        if (teacher == null) {
            throw new EntityNotFoundException(Teacher.class, teacher.getTeacherId());
        }
        teacherDao.save(teacher);
        log.debug("Teacher {} {} updated.", teacher.getFirstName(), teacher.getLastName());
    }

    @Override
    public void deleteTeacher(Teacher teacher) throws EntityNotFoundException {
        if (teacher == null) {
            throw new EntityNotFoundException(Teacher.class, teacher.getTeacherId());
        }
        teacherDao.delete(teacher);
        log.debug("Teacher {} {} deleted.", teacher.getFirstName(), teacher.getLastName());
    }

    @Override
    public List<Teacher> findAll() throws DatabaseException {
        return teacherDao.findAll();
    }

    @Override
    public Teacher findTeacherById(Long id) throws EntityNotFoundException {
        if (id <= 0 || id == null) {
            throw new EntityNotFoundException(Teacher.class, id);
        }
        Teacher teacher = teacherDao.findTeacherByTeacherId(id);
        if (teacher == null) {
            throw new EntityNotFoundException(Teacher.class, teacher.getTeacherId());
        }
        log.debug("Teacher {} {} founded.", teacher.getFirstName(), teacher.getLastName());
        return teacher;
    }

    @Override
    public void assignSchedule(ScheduleItem scheduleItem, Teacher teacher) throws EntityNotFoundException {
        if (scheduleItem == null) {
            throw new EntityNotFoundException(ScheduleItem.class, scheduleItem.getId());
        }
        if (teacher == null) {
            throw new EntityNotFoundException(Teacher.class, teacher.getTeacherId());
        }
        teacher.getScheduleItems().add(scheduleItem);
        teacherDao.save(teacher);
        log.debug("Schedule: group - {}, course - {}, auditory - {} assigned to teacher {} {}.",
                scheduleItem.getGroup().getGroupName(), scheduleItem.getCourse().getCourseName(),
                scheduleItem.getClassRoom().getClassRoom(), teacher.getFirstName(), teacher.getLastName());
    }

}
