package com.foxminded;

import com.foxminded.domain.*;
import com.foxminded.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Components implements ApplicationRunner {

    @Autowired
    private StudentDomain studentDomain;
    @Autowired
    private GroupDomain groupDomain;
    @Autowired
    private CourseDomain courseDomain;
    @Autowired
    private TeacherDomain teacherDomain;
    @Autowired
    private ClassRoomDomain classRoomDomain;
    @Autowired
    private ScheduleItemDomain scheduleItemDomain;
    @Autowired
    private UniversityDomain universityDomain;

    public void createUniversity() {

        List<Group> group = groupDomain.createGroup(22);
        studentDomain.createStudents(group, 8, 18);
//        List<Teacher> teachers = teacherDomain.createTeacher(12);
//        List<ClassRoom> classRooms = classRoomDomain.createClassRooms(20);
//        List<Course> courses = courseDomain.createCourses();
//        List<ScheduleItem> scheduleItems = scheduleItemDomain.createScheduleItems(group, teachers, courses);
//        courseDomain.assignCourses(scheduleItems, teachers);
//        universityDomain.createSchedule(scheduleItems, classRooms);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createUniversity();
    }
}
