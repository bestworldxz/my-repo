package com.foxminded.service;

import com.foxminded.model.ClassRoom;
import com.foxminded.model.ScheduleItem;

public interface UniversityService {

    void createSchedule(ScheduleItem scheduleItem, ClassRoom classRoom);
}
