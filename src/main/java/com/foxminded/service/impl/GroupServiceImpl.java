package com.foxminded.service.impl;

import com.foxminded.dao.GroupDao;
import com.foxminded.dao.StudentDao;
import com.foxminded.model.Group;
import com.foxminded.model.Student;
import com.foxminded.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    private GroupDao groupDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public Group createGroup(String groupName) {
        if (groupName.equals("")) {
            throw new IllegalArgumentException("Group name cannot be empty");
        }
        Group group = new Group(groupName);
        groupDao.save(group);
        logger.debug("Group {} created.", groupName);
        return group;
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public void delete(Group group) {
        if (group == null) {
            throw new NullPointerException("Group cannot be null");
        }
        groupDao.delete(group);
        logger.debug("Group {} deleted.", group.getGroupName());
    }

    @Override
    public Group findGroupById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot has negative value.");
        }
        Group group = groupDao.findGroupByGroupId(id);
        if (group == null) {
            throw new NullPointerException(String.format("Group with id %d not found.", id));
        }
        return group;
    }

    @Override
    public Group update(Group group) {
        if (group == null) {
            throw new NullPointerException("Group cannot be null");
        }
        groupDao.save(group);
        logger.debug("Group {} updated.", group.getGroupName());
        return group;
    }

    @Override
    public void assignStudents(Group group, Student student) {
        if (group == null || student == null) {
            throw new NullPointerException("Object cannot be null");
        }
        group.getStudents().add(student);
        student.setGroup(group);
        groupDao.save(group);
        studentDao.save(student);
        logger.debug("Student {} {} added to group {}.", student.getFirstName(), student.getLastName(), group.getGroupName());
    }
}

