package com.foxminded.domain;

import com.foxminded.model.Course;
import com.foxminded.service.CourseService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseDomain {
    private static final String[] COURSE_NAMES = {"Math", "English", "Java", "Literature", "Physic",
            "Physical education", "Ukrainian", "Chemistry", "Russian"};
    private CourseService courseService;

    public CourseDomain(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<Course> createCourses() {
        List<Course> courses = new ArrayList<>();
        for (String course_name : COURSE_NAMES) {
            courses.add(courseService.createCourse(course_name));
        }
        return courses;
    }
}
