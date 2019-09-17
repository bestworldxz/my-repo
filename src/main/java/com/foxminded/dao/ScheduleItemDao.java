package com.foxminded.dao;

import com.foxminded.model.Group;
import com.foxminded.model.ScheduleItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleItemDao extends CrudRepository<ScheduleItem, Long> {

    @Override
    List<ScheduleItem> findAll();

    List<ScheduleItem> findByGroup(Group group);

    List<ScheduleItem> findByGroupAndDate(Group group, LocalDate date);

    List<ScheduleItem> findByDate(LocalDate date);
}
