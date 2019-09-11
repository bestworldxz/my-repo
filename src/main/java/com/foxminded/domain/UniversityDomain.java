package com.foxminded.domain;

import com.foxminded.model.ClassRoom;
import com.foxminded.model.ScheduleItem;
import com.foxminded.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class UniversityDomain {

    private static final Logger logger = LoggerFactory.getLogger(UniversityDomain.class);

    private final UniversityService universityService;

    public UniversityDomain(UniversityService universityService) {
        this.universityService = universityService;
    }

    public void createSchedule(List<ScheduleItem> scheduleItems, List<ClassRoom> classRooms) {
        if (scheduleItems == null || classRooms == null) {
            throw new NullPointerException();
        } else {
            for (ScheduleItem scheduleItem : scheduleItems) {
                int auditoryIndex = ThreadLocalRandom.current().nextInt(classRooms.size());
                universityService.createSchedule(scheduleItem, classRooms.get(auditoryIndex));
            }
        }
    }
}
