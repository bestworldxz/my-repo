package com.foxminded.service;

import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Group;
import com.foxminded.model.Student;

import java.util.List;

public interface GroupService {

    Group createGroup(String groupName) throws EntityNotFoundException;

    List<Group> findAll();

    void delete(Group group) throws EntityNotFoundException;

    Group findGroupById(long id) throws EntityNotFoundException;

    Group update(Group group) throws EntityNotFoundException;

    void assignStudents(Group group, Student student) throws EntityNotFoundException;

}
