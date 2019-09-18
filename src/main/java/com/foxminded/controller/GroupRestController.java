package com.foxminded.controller;

import com.foxminded.exception.DatabaseException;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Group;
import com.foxminded.model.Student;
import com.foxminded.service.GroupService;
import com.foxminded.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupRestController {

    private GroupService groupService;
    private StudentService studentService;

    @Autowired
    public GroupRestController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    Group findOne(@PathVariable Long id) throws EntityNotFoundException {
        return groupService.findGroupById(id);
    }

    @GetMapping()
    public List<Group> groupList() throws DatabaseException {
        return groupService.findAll();
    }

    @PostMapping(value = "{groupName}")
    public Group create(@PathVariable("groupName") String groupName) {
        return groupService.createGroup(groupName);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Long groupId) {
        Group group = groupService.findGroupById(groupId);
        groupService.delete(group);
    }

    @PutMapping(value = "{id}/{newGroupName}")
    public Group update(@PathVariable("id") Long id, @PathVariable("newGroupName") String newGroupName) {
        Group group = groupService.findGroupById(id);
        return groupService.renameGroup(newGroupName, group);
    }

    @PutMapping(value = "{id}/{firstName}/{lastName}")
    public Group addStudent(@PathVariable("id") Long id, @PathVariable("firstName") String firstName,
                            @PathVariable("lastName") String lastName) {
        Group group = groupService.findGroupById(id);
        Student student = studentService.createStudent(firstName, lastName);
        groupService.assignStudents(group, student);
        return group;
    }
}
