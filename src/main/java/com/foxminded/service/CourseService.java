package com.foxminded.service;

import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Course;
import com.foxminded.model.Teacher;

import java.util.List;

public interface CourseService {

    Course createCourse(String courseName) throws EntityNotFoundException;

    void deleteCourse(Course course) throws EntityNotFoundException;

    void updateCourse(Course course) throws EntityNotFoundException;

    List<Course> findAll();

    void assignCourses(Course course, Teacher teacher) throws EntityNotFoundException;

    Course findByCourseName(String courseName);
}
