package com.foxminded.service;

import com.foxminded.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(String firstName, String lastName);

    List<Student> findAll();

    void delete(Student student);

    Student findStudentById(long id);

    void update(Student student);
}
