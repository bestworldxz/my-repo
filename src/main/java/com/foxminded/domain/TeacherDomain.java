package com.foxminded.domain;


import com.foxminded.model.Course;
import com.foxminded.model.ScheduleItem;

import com.foxminded.model.Teacher;
import com.foxminded.service.TeacherService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class TeacherDomain {
    private static final String[] FIRST_NAMES = {"Tudor", "Kieron", "Lorena", "Menna", "Millie-Mae",
            "Naveed", "Debra", "Toni", "John", "Mcauley"};
    private static final String[] LAST_NAMES = {"Whitworth", "Turnbull", "Dougherty", "Lugo", "Lester",
            "Munoz", "Padilla", "Hamilton", "Nichols", "Calvert"};

    private final TeacherService teacherService;

    public TeacherDomain(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public List<Teacher> createTeacher(int numberOfTeacher) {
        if (numberOfTeacher < 0) {
            throw new IllegalArgumentException();
        }
        List<Teacher> teachers = new ArrayList<>();
        while (numberOfTeacher > 0) {
            Teacher teacher = generateTeacher();
            teachers.add(teacher);
            numberOfTeacher--;
        }
        return teachers;
    }

    private Teacher generateTeacher() {
        String[] teacherInfo = generateName();
        return teacherService.createTeacher(teacherInfo[0], teacherInfo[1]);
    }

    private String[] generateName() {
        int firstNameIndex = ThreadLocalRandom.current().nextInt(FIRST_NAMES.length);
        int lastNameIndex = ThreadLocalRandom.current().nextInt(LAST_NAMES.length);
        return new String[]{FIRST_NAMES[firstNameIndex], LAST_NAMES[lastNameIndex]};
    }
}
