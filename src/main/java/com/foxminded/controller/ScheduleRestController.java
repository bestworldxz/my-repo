package com.foxminded.controller;

import com.foxminded.model.ScheduleItem;
import com.foxminded.service.ScheduleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "schedule-items")
public class ScheduleRestController {
    private ScheduleItemService scheduleItemService;

    @Autowired
    public ScheduleRestController(ScheduleItemService scheduleItemService) {
        this.scheduleItemService = scheduleItemService;
    }

    @GetMapping
    public List<ScheduleItem> getAll(){
        return scheduleItemService.findAll();
    }

    @GetMapping(value = "{date}")
    public List<ScheduleItem> getScheduleForDate(@PathVariable("date") String date){
        LocalDate day = LocalDate.parse(date);
        return scheduleItemService.findAll().stream().filter(d -> d.getDate().equals(day))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "{groupName}/{date}")
    public List<ScheduleItem> getDayScheduleForGroup(@PathVariable("groupName") String groupName,
                                                     @PathVariable("date") String date){
        LocalDate day = LocalDate.parse(date);
        return scheduleItemService.findAll().stream().filter(d -> d.getDate().equals(day))
                .filter(g -> g.getGroup().getGroupName().equals(groupName))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "{groupName}")
    public List<ScheduleItem> getMonthScheduleForGroup(@PathVariable("groupName") String groupName){
        return scheduleItemService.findAll().stream()
                .filter(g -> g.getGroup().getGroupName().equals(groupName))
                .collect(Collectors.toList());
    }
}
