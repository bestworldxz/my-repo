package com.foxminded.controller;

import com.foxminded.model.Group;
import com.foxminded.model.ScheduleItem;
import com.foxminded.service.GroupService;
import com.foxminded.service.ScheduleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<ScheduleItem> getAll() {
        return scheduleItemService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ScheduleItem> getScheduleForDate(@RequestParam(value = "date") String date){
        LocalDate day = LocalDate.parse(date);
        return scheduleItemService.findDaySchedule(day);
    }

    @GetMapping(value = "{groupName}/{date}")
    public List<ScheduleItem> getDayScheduleForGroup(@PathVariable("groupName") String groupName,
                                                     @PathVariable("date") String date){
        LocalDate day = LocalDate.parse(date);
        Group group = groupService.findGroupByName(groupName);
        return scheduleItemService.findDayScheduleForGroup(day, group);
    }

//    @GetMapping(value = "{id}")
//    public List<ScheduleItem> getMonthScheduleForGroup(@PathVariable("id") Long id) {
//        Group group = groupService.findGroupById(id);
//        return scheduleItemService.findMonthSchedule(group);
//    }
}
