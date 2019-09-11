package com.foxminded.dao;

import com.foxminded.model.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupDao extends CrudRepository<Group, Long> {

    @Override
    List<Group> findAll();

    Group findGroupByGroupId(long id);
}
