package com.foxminded.domain;

import com.foxminded.model.Course;
import com.foxminded.model.Group;
import com.foxminded.model.ScheduleItem;
import com.foxminded.model.Teacher;
import com.foxminded.service.ScheduleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ScheduleItemDomain {

    private static final int MIN_NUMBER_OF_COURSE = 3;
    private static final int MAX_NUMBER_OF_COURSE = 6;
    private static final LocalDate START_DATE = LocalDate.parse("2019-09-01");
    private static final LocalDate END_DATE = LocalDate.parse("2019-09-30");

    private final ScheduleItemService scheduleItemService;

    @Autowired
    public ScheduleItemDomain(ScheduleItemService scheduleItemService) {
        this.scheduleItemService = scheduleItemService;
    }

    public List<ScheduleItem> createScheduleItems(List<Group> groups, List<Teacher> teachers) {
        List<ScheduleItem> scheduleItems = new ArrayList<>();
        if (groups == null || teachers == null) {
            throw new NullPointerException();
        }
        for (Group group : groups) {
            LocalDate next = START_DATE.minusDays(1);
            while ((next = next.plusDays(1)).isBefore(END_DATE.plusDays(1))) {
                if (!(next.getDayOfWeek() == DayOfWeek.SATURDAY || next.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                    int numberOfCoursesPerDay = ThreadLocalRandom.current().nextInt(MIN_NUMBER_OF_COURSE, MAX_NUMBER_OF_COURSE);
                    while (numberOfCoursesPerDay > 0) {
                        int teacherIndex = ThreadLocalRandom.current().nextInt(teachers.size());
                        List<Course> courses = teachers.get(teacherIndex).getCourses();
                        int courseIndex = ThreadLocalRandom.current().nextInt(courses.size());
                        ScheduleItem scheduleItem = scheduleItemService.createScheduleItem(next, group, teachers.get(teacherIndex), courses.get(courseIndex));
                        scheduleItems.add(scheduleItem);
                        numberOfCoursesPerDay--;
                    }
                }
            }
        }
        return scheduleItems;
    }
}
