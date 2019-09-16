package com.foxminded.controller;

import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Course;
import com.foxminded.model.Teacher;
import com.foxminded.service.CourseService;
import com.foxminded.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teachers")
public class TeacherRestController {


    private TeacherService teacherService;
    private CourseService courseService;

    @Autowired
    public TeacherRestController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping()
    public List<Teacher> teacherList() {
        return teacherService.findAll();
    }

    @GetMapping(value = "{id}")
    public Teacher getTeacher(@PathVariable("id") Long teacherId) throws EntityNotFoundException {
        return teacherService.findTeacherById(teacherId);
    }

    @PostMapping(value = "{firstName}/{lastName}")
    public Teacher createTeacher(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return teacherService.createTeacher(firstName, lastName);
    }

    @DeleteMapping(value = "{id}")
    public void deleteTeacher(@PathVariable("id") Long id) throws EntityNotFoundException {
        Teacher teacher = teacherService.findTeacherById(id);
        teacherService.deleteTeacher(teacher);
    }

    @DeleteMapping(value = "{id}/{courseName}")
    public void deleteCourse(@PathVariable("id") Long id,
                             @PathVariable("courseName") String courseName) throws EntityNotFoundException {
        Course course = teacherService.findTeacherById(id).getCourses()
                .stream().filter(c -> c.getCourseName().equals(courseName))
                .findAny().orElse(null);
        courseService.deleteCourse(course);
    }

    @PutMapping(value = "{id}/{courseName}")
    public Teacher addCourse(@PathVariable("id") Long id, @PathVariable("courseName") String courseName) throws EntityNotFoundException {
        Teacher teacher = teacherService.findTeacherById(id);
        Course course = courseService.createCourse(courseName);
        courseService.assignCourses(course, teacher);
        return teacher;
    }

}
