package com.foxminded.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private long teacherId;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<ScheduleItem> scheduleItems;

    @OneToMany(mappedBy = "teacher", orphanRemoval = true)
    @JsonManagedReference
    private List<Course> courses;

    public Teacher(String firstName, String lastName) {
        this(0, firstName, lastName, new ArrayList<>(), new ArrayList<>());
    }

}

