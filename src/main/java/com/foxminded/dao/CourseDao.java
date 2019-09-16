package com.foxminded.dao;

import com.foxminded.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends CrudRepository<Course, Long> {

    @Override
    List<Course> findAll();

    Course findCourseByCourseName(String courseName);

}
