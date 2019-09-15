package com.foxminded.service.impl;

import com.foxminded.dao.CourseDao;
import com.foxminded.dao.TeacherDao;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Course;
import com.foxminded.model.Teacher;
import com.foxminded.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;
    private TeacherDao teacherDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao, TeacherDao teacherDao) {
        this.courseDao = courseDao;
        this.teacherDao = teacherDao;
    }

    @Override
    public Course createCourse(String courseName) throws EntityNotFoundException {
        if (courseName.equals("")) {
            throw new EntityNotFoundException(Course.class, courseName);
        }
        Course course = new Course(courseName);
        log.debug("Course {} created.", courseName);
        courseDao.save(course);
        return course;
    }

    @Override
    public void deleteCourse(Course course) throws EntityNotFoundException {
        if (course == null) {
            throw new EntityNotFoundException(Course.class, course.getCourseId());
        }
        courseDao.delete(course);
        log.debug("Course {} deleted.", course.getCourseName());
    }

    @Override
    public void updateCourse(Course course) throws EntityNotFoundException {
        if (course == null) {
            throw new EntityNotFoundException(Course.class, course.getCourseId());
        }
        courseDao.save(course);
        log.debug("Course {} updated.", course.getCourseName());
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public void assignCourses(Course course, Teacher teacher) throws EntityNotFoundException {
        if (course == null) {
            throw new EntityNotFoundException(Course.class, course.getCourseId());
        }
        if (teacher == null) {
            throw new EntityNotFoundException(Teacher.class, teacher.getTeacherId());
        }
        teacher.getCourses().add(course);
        course.setTeacher(teacher);
        courseDao.save(course);
        teacherDao.save(teacher);
        log.debug("Course {} assigned to teacher {} {}.", course.getCourseName(),
                teacher.getFirstName(), teacher.getLastName());
    }

    @Override
    public Course findByCourseName(String courseName) {
        return courseDao.findByCourseNameIn(courseName);
    }
}
