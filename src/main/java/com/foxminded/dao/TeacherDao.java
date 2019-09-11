package com.foxminded.dao;

import com.foxminded.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao extends CrudRepository<Teacher, Long> {

    @Override
    List<Teacher> findAll();

    Teacher findTeacherByTeacherId(long id);
}
