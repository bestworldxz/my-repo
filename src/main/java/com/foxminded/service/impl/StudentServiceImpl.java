package com.foxminded.service.impl;

import com.foxminded.dao.StudentDao;
import com.foxminded.model.Student;
import com.foxminded.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student createStudent(String firstName, String lastName) {
        if (firstName.equals("") || lastName.equals("")) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        Student student = new Student(firstName, lastName);
        studentDao.save(student);
        log.debug("Student {} {} created.", firstName, lastName);
        return student;
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public void delete(Student student) {
        if (student == null) {
            throw new NullPointerException("Student name cannot be empty.");
        }
        studentDao.delete(student);
        log.debug("Student {} {} deleted.", student.getFirstName(), student.getLastName());
    }

    @Override
    public Student findStudentById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be zero or negative.");
        }
        Student student = studentDao.findStudentByStudentId(id);
        if (student != null) {
            log.debug("Student {} {} founded.", student.getFirstName(), student.getLastName());
        } else {
            throw new NullPointerException("Student not found.");
        }
        return student;
    }

    @Override
    public void update(Student student) {
        if (student == null) {
            throw new NullPointerException("Student cannot be null.");
        }
        studentDao.save(student);
        log.debug("Student {} {} updated.", student.getFirstName(), student.getFirstName());
    }
}
