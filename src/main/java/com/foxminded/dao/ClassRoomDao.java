package com.foxminded.dao;

import com.foxminded.model.ClassRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoomDao extends CrudRepository<ClassRoom, Long> {

    @Override
    List<ClassRoom> findAll();
}
