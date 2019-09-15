package com.foxminded.domain;

import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Course;
import com.foxminded.model.ScheduleItem;
import com.foxminded.model.Teacher;
import com.foxminded.service.CourseService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class CourseDomain {
    private static final String[] COURSE_NAMES = {"Math", "English", "Java", "Literature", "Physic",
            "Physical education", "Ukrainian", "Chemistry", "Russian"};
    private CourseService courseService;

    public CourseDomain(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<Course> createCourses() throws EntityNotFoundException {
        List<Course> courses = new ArrayList<>();
        for (String course_name : COURSE_NAMES) {
            courses.add(courseService.createCourse(course_name));
        }
        return courses;
    }

    public void assignCourses(List<Course> courses, List<Teacher> teachers) throws EntityNotFoundException {
        if (courses == null || teachers == null) {
            throw new NullPointerException("Objects cannot be null");
        }
        int index = 0;
        for (Teacher teacher : teachers) {
            courseService.assignCourses(courses.get(index++), teacher);
        }
    }
}
