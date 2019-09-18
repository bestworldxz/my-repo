package com.foxminded.service.impl;

import com.foxminded.dao.StudentDao;
import com.foxminded.exception.DatabaseException;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.exception.WrongArgumentException;
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
    public Student createStudent(String firstName, String lastName) throws WrongArgumentException{
        if (firstName.equals(" ") || firstName.isEmpty() || firstName.matches("[0-9]+")) {
            throw new WrongArgumentException(Student.class, firstName, lastName);
        }
        if (lastName.equals(" ") || lastName.isEmpty() || lastName.matches("[0-9]+")) {
            throw new WrongArgumentException(Student.class, firstName, lastName);
        }
        Student student = new Student(firstName, lastName);
        studentDao.save(student);
        log.debug("Student {} {} created.", firstName, lastName);
        return student;
    }

    @Override
    public List<Student> findAll() throws DatabaseException {
        List<Student> students = studentDao.findAll();
        if (students.isEmpty()){
            throw new DatabaseException(Student.class);
        }
        return studentDao.findAll();
    }

    @Override
    public void delete(Student student) throws EntityNotFoundException{
        if (student == null) {
            throw new EntityNotFoundException(Student.class, student.getStudentId());
        }
        studentDao.delete(student);
        log.debug("Student {} {} deleted.", student.getFirstName(), student.getLastName());
    }

    @Override
    public Student findStudentById(Long id) throws EntityNotFoundException{
        if (id == null) {
            throw new EntityNotFoundException(Student.class, id);
        }
        Student student = studentDao.findStudentByStudentId(id);
        if (student == null) {
            throw new EntityNotFoundException(Student.class, student.getStudentId());
        }
        log.debug("Student {} {} founded.", student.getFirstName(), student.getLastName());
        return student;
    }

    @Override
    public void update(Student student) throws EntityNotFoundException{
        if (student == null) {
            throw new EntityNotFoundException(Student.class, student.getStudentId());
        }
        studentDao.save(student);
        log.debug("Student {} {} updated.", student.getFirstName(), student.getFirstName());
    }
}
