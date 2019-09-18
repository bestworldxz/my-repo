package com.foxminded.service;

import com.foxminded.exception.DatabaseException;
import com.foxminded.model.Group;
import com.foxminded.model.Student;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Group createGroup(String groupName);

    List<Group> findAll() throws DatabaseException;

    void delete(Group group);

    Group findGroupById(Long id);

    Group update(Group group);

    void assignStudents(Group group, Student student);

    Group renameGroup(String newGroupName, Group group);

}
