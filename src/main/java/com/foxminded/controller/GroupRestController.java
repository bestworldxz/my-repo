package com.foxminded.controller;

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

    @GetMapping(value = "{id}")
    public Group getGroup(@PathVariable("id") Long groupId) throws EntityNotFoundException {
        return groupService.findGroupById(groupId);
    }

    @GetMapping()
    public List<Group> groupList() {
        return groupService.findAll();
    }

    @PostMapping(value = "{groupName}")
    public Group create(@PathVariable("groupName") String groupName) throws EntityNotFoundException {
        return groupService.createGroup(groupName);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Long groupId) throws EntityNotFoundException {
        Group group = groupService.findGroupById(groupId);
        groupService.delete(group);
    }

    @PutMapping(value = "{id}/{newGroupName}")
    public Group update(@PathVariable("id") Long id, @PathVariable("newGroupName") String newGroupName) throws EntityNotFoundException {
        Group group = groupService.findGroupById(id);
        group.setGroupName(newGroupName);
        groupService.update(group);
        return group;
    }

    @PutMapping(value = "{id}/{firstName}/{lastName}")
    public Group addStudent(@PathVariable("id") Long id, @PathVariable("firstName") String firstName,
                           @PathVariable("lastName") String lastName) throws EntityNotFoundException {
        Group group = groupService.findGroupById(id);
        Student student = studentService.createStudent(firstName, lastName);
        groupService.assignStudents(group, student);
        return group;
    }
}
