package com.foxminded.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;

    @Column(name = "course_name")
    private String courseName;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    public Course(String courseName) {
        this(0, courseName, null);
    }

}
