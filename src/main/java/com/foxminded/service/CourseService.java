package com.foxminded.service;

import com.foxminded.model.Course;
import com.foxminded.model.Teacher;

import java.util.List;

public interface CourseService {

    Course createCourse(String courseName);

    void deleteCourse(Course course);

    void updateCourse(Course course);

    List<Course> findAll();

    void assignCourses(Course course, Teacher teacher);
}
