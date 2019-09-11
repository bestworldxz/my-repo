package com.foxminded.service;

import com.foxminded.model.Group;
import com.foxminded.model.Student;

import java.util.List;

public interface GroupService {

    Group createGroup(String groupName);

    List<Group> findAll();

    void delete(Group group);

    Group findGroupById(long id);

    void update(Group group);

    void assignStudents(Group group, Student student);

}
