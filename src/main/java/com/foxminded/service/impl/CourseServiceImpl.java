package com.foxminded.service.impl;

import com.foxminded.dao.CourseDao;
import com.foxminded.model.Course;
import com.foxminded.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseDao courseDao;

    @Override
    public Course createCourse(String courseName) {
        if (courseName.equals("")) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        Course course = new Course(courseName);
        logger.debug("Course {} created.", courseName);
        courseDao.save(course);
        return course;
    }

    @Override
    public void deleteCourse(Course course) {
        if (course == null) {
            throw new NullPointerException("Course cannot be null.");
        }
        courseDao.delete(course);
        logger.debug("Course {} deleted.", course.getCourseName());
    }

    @Override
    public void updateCourse(Course course) {
        if (course == null) {
            throw new NullPointerException("Course cannot be null.");
        }
        courseDao.save(course);
        logger.debug("Course {} updated.", course.getCourseName());
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }
}
