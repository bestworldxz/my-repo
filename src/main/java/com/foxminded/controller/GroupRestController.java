package com.foxminded.controller;

import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Group;
import com.foxminded.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupRestController {

    @Autowired
    private GroupService groupService;

    @GetMapping(value = "{id}")
    public Group getGroup(@PathVariable("id") Long groupId) throws EntityNotFoundException {
        return groupService.findGroupById(groupId);
    }

    @GetMapping()
    public List<Group> groupList() {
        return groupService.findAll();
    }

    @PostMapping(value = "{groupName}")
    public Group create(@PathVariable("groupName") String groupName) {
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


}
