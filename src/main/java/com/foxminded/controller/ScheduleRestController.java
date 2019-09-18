package com.foxminded.controller;

import com.foxminded.exception.DatabaseException;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.exception.WrongArgumentException;
import com.foxminded.model.Group;
import com.foxminded.model.ScheduleItem;
import com.foxminded.service.GroupService;
import com.foxminded.service.ScheduleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping(value = "schedule-items")
public class ScheduleRestController {

    private ScheduleItemService scheduleItemService;
    private GroupService groupService;

    @Autowired
    public ScheduleRestController(ScheduleItemService scheduleItemService, GroupService groupService) {
        this.scheduleItemService = scheduleItemService;
        this.groupService = groupService;
    }

    @GetMapping(value = "/")
    public List<ScheduleItem> getAll() throws DatabaseException {
        return scheduleItemService.findAll();
    }

    @GetMapping(value = "{date}")
    public List<ScheduleItem> getScheduleForDate(@PathVariable(value = "date") String date)
            throws DatabaseException, EntityNotFoundException, WrongArgumentException, DateTimeParseException {
        LocalDate day = LocalDate.parse(date);
        return scheduleItemService.findDaySchedule(day);
    }

    @GetMapping(value = "{id}/{date}")
    public List<ScheduleItem> getDayScheduleForGroup(@PathVariable("id") Long id,
                                                     @PathVariable("date") String date)
            throws DatabaseException, EntityNotFoundException, WrongArgumentException, DateTimeParseException {
        LocalDate day = LocalDate.parse(date);
        Group group = groupService.findGroupById(id);
        return scheduleItemService.findDayScheduleForGroup(day, group);
    }

}
