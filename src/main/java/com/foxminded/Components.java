package com.foxminded;

import com.foxminded.domain.*;
import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Components implements ApplicationRunner {

    private StudentDomain studentDomain;
    private GroupDomain groupDomain;
    private CourseDomain courseDomain;
    private TeacherDomain teacherDomain;
    private ClassRoomDomain classRoomDomain;
    private ScheduleItemDomain scheduleItemDomain;
    private UniversityDomain universityDomain;

    @Autowired
    public Components(StudentDomain studentDomain, GroupDomain groupDomain,
                      CourseDomain courseDomain, TeacherDomain teacherDomain,
                      ClassRoomDomain classRoomDomain, ScheduleItemDomain scheduleItemDomain,
                      UniversityDomain universityDomain) {
        this.studentDomain = studentDomain;
        this.groupDomain = groupDomain;
        this.courseDomain = courseDomain;
        this.teacherDomain = teacherDomain;
        this.classRoomDomain = classRoomDomain;
        this.scheduleItemDomain = scheduleItemDomain;
        this.universityDomain = universityDomain;
    }

    public void createUniversity() throws EntityNotFoundException {

        List<Group> group = groupDomain.createGroup(22);
        studentDomain.createStudents(group, 8, 18);
        List<Teacher> teachers = teacherDomain.createTeacher(9);
        List<ClassRoom> classRooms = classRoomDomain.createClassRooms(20);
        List<Course> courses = courseDomain.createCourses();
        courseDomain.assignCourses(courses, teachers);
        List<ScheduleItem> scheduleItems = scheduleItemDomain.createScheduleItems(group, teachers);
        universityDomain.createSchedule(scheduleItems, classRooms);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createUniversity();
    }
}
