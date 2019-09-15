package com.foxminded.domain;

import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Group;
import com.foxminded.model.Student;
import com.foxminded.service.GroupService;
import com.foxminded.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class StudentDomain {
    private static final String[] FIRST_NAMES = {"Yan", "Loma", "Beulah", "Toni", "Daren",
            "Ariane", "Tarah", "Katlyn", "Janean", "Annamae"};
    private static final String[] LAST_NAMES = {"Hohl", "Coggin", "Accetta", "Mai", "Conyer",
            "Pickel", "Taplin", "Goulding", "Tan", "Scates"};

    private StudentService studentService;
    private GroupService groupService;

    public StudentDomain(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    public void createStudents(List<Group> groups, int minNumberOfStudents, int maxNumberOfStudents) throws EntityNotFoundException {
        if (minNumberOfStudents < 0 || maxNumberOfStudents < 0 || minNumberOfStudents > maxNumberOfStudents) {
            throw new IllegalArgumentException();
        }
        for (Group group : groups) {
            int numberOfStudents = ThreadLocalRandom.current().nextInt(minNumberOfStudents, maxNumberOfStudents);
            while (numberOfStudents > 0) {
                Student student = generateStudent();
                groupService.assignStudents(group, student);
                numberOfStudents--;
            }
        }
    }

    private Student generateStudent() {
        String[] studentInfo = generateName();
        return studentService.createStudent(studentInfo[0], studentInfo[1]);
    }

    private String[] generateName() {
        int firstNameIndex = ThreadLocalRandom.current().nextInt(FIRST_NAMES.length);
        int lastNameIndex = ThreadLocalRandom.current().nextInt(LAST_NAMES.length);
        return new String[]{FIRST_NAMES[firstNameIndex], LAST_NAMES[lastNameIndex]};
    }
}

