package com.foxminded.service;

import com.foxminded.exception.DatabaseException;
import com.foxminded.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(String firstName, String lastName);

    List<Student> findAll() throws DatabaseException;

    void delete(Student student);

    Student findStudentById(Long id);

    void update(Student student);
}
