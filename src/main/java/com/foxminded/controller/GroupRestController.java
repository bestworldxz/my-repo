package com.foxminded.controller;

import com.foxminded.model.Group;
import com.foxminded.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupRestController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Group> getGroup(@PathVariable("id") Long groupId) {
        if (groupId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Group group = groupService.findGroupById(groupId);

        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Group>> groupList() {
        List<Group> groups = groupService.findAll();
        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @RequestMapping(value = "{groupName}", method = RequestMethod.POST)
    public ResponseEntity<Group> create(@PathVariable("groupName") String groupName) {
        if (groupName.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Group group = groupService.createGroup(groupName);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Group> delete(@PathVariable("id") Long groupId) {
        if (groupId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Group group = groupService.findGroupById(groupId);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        groupService.delete(group);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/{newGroupName}", method = RequestMethod.PUT)
    public ResponseEntity<Group> update(@PathVariable("id") Long id, @PathVariable("newGroupName") String newGroupName) {
        if (id == null || newGroupName.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Group group = groupService.findGroupById(id);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        group.setGroupName(newGroupName);
        groupService.update(group);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }


}
