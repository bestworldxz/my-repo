package com.foxminded.dao;

import com.foxminded.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends CrudRepository<Student, Long> {

    @Override
    List<Student> findAll();

    Student findStudentByStudentId(long id);
}
