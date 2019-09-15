package com.foxminded.service.impl;

import com.foxminded.dao.GroupDao;
import com.foxminded.dao.StudentDao;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Group;
import com.foxminded.model.Student;
import com.foxminded.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;
    private StudentDao studentDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao, StudentDao studentDao) {
        this.groupDao = groupDao;
        this.studentDao = studentDao;
    }

    @Override
    public Group createGroup(String groupName) throws EntityNotFoundException {
        if (groupName.isEmpty()) {
            throw new EntityNotFoundException(Group.class, groupName);
        }
        Group group = new Group(groupName);
        groupDao.save(group);
        log.debug("Group {} created.", groupName);
        return group;
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public void delete(Group group) throws EntityNotFoundException {
        if (group == null) {
            throw new EntityNotFoundException(Group.class, group.getGroupId());
        }
        groupDao.delete(group);
        log.debug("Group {} deleted.", group.getGroupName());
    }

    @Override
    public Group findGroupById(long id) throws EntityNotFoundException {
        Group group = groupDao.findGroupByGroupId(id);
        if (group == null) {
            throw new EntityNotFoundException(Group.class, id);
        }
        return group;
    }

    @Override
    public Group update(Group group) throws EntityNotFoundException {
        if (group == null) {
            throw new EntityNotFoundException(Group.class, group.getGroupId());
        }
        groupDao.save(group);
        log.debug("Group {} updated.", group.getGroupName());
        return group;
    }

    @Override
    public void assignStudents(Group group, Student student) throws EntityNotFoundException {
        if (group == null) {
            throw new EntityNotFoundException(Group.class, group.getGroupId());
        }
        if (student == null) {
            throw new EntityNotFoundException(Student.class, student.getStudentId());
        }
        group.getStudents().add(student);
        student.setGroup(group);
        groupDao.save(group);
        studentDao.save(student);
        log.debug("Student {} {} added to group {}.", student.getFirstName(), student.getLastName(), group.getGroupName());
    }
}

