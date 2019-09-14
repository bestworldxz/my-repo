package com.foxminded.controller;

import com.foxminded.model.Group;
import com.foxminded.model.Teacher;
import com.foxminded.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/teachers")
public class TeacherRestController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Teacher> getTeacher(@PathVariable("id") Long teacherId) {
        if (teacherId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Teacher teacher = teacherService.findTeacherById(teacherId);

        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Teacher>> groupList() {
        List<Teacher> teachers = teacherService.findAll();
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
}
