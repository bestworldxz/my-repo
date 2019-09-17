package com.foxminded.service.impl;

import com.foxminded.dao.GroupDao;
import com.foxminded.dao.StudentDao;
import com.foxminded.exception.DatabaseException;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.exception.WrongArgumentException;
import com.foxminded.model.Group;
import com.foxminded.model.Student;
import com.foxminded.service.GroupService;
import lombok.extern.slf4j.Slf4j;
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
    public Group createGroup(String groupName) throws WrongArgumentException {
        if (groupName.isEmpty() || groupName.equals(" ")) {
            throw new WrongArgumentException(Group.class, groupName);
        }
        Group group = new Group(groupName);
        groupDao.save(group);
        log.debug("Group {} created.", groupName);
        return group;
    }

    @Override
    public List<Group> findAll() throws DatabaseException {
        List<Group> groups = groupDao.findAll();
        if (groups.isEmpty()){
            throw new DatabaseException(Group.class);
        }
        return groupDao.findAll();
    }

    @Override
    public void delete(Group group) {
        if (group == null) {
            throw new EntityNotFoundException(Group.class, group.getGroupId());
        }
        groupDao.delete(group);
        log.debug("Group {} deleted.", group.getGroupName());
    }

    @Override
    public Group findGroupById(Long id) {
        if (id == null || id <= 0){
            throw new EntityNotFoundException(Group.class, id);
        }
        Group group = groupDao.findGroupByGroupId(id);
        if (group == null) {
            throw new EntityNotFoundException(Group.class, id);
        }
        return group;
    }

    @Override
    public Group update(Group group) {
        if (group == null) {
            throw new EntityNotFoundException(Group.class, group.getGroupId());
        }
        groupDao.save(group);
        log.debug("Group {} updated.", group.getGroupName());
        return group;
    }

    @Override
    public void assignStudents(Group group, Student student) {
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

    @Override
    public Group findGroupByName(String groupName) throws WrongArgumentException {
        if (groupName.isEmpty() || groupName.equals(" ")) {
            throw new WrongArgumentException(Group.class, groupName);
        }
        return groupDao.findByGroupName(groupName);
    }

    @Override
    public Group renameGroup(String newGroupName, Group group) throws WrongArgumentException {
        if (newGroupName.equals(" ") || newGroupName.isEmpty()){
            throw new WrongArgumentException(Group.class, newGroupName);
        }
        if (group == null){
            throw new EntityNotFoundException(Group.class, group.getGroupId());
        }
        group.setGroupName(newGroupName);
        groupDao.save(group);
        return group;
    }
}

