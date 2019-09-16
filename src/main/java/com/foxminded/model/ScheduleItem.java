package com.foxminded.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "schedule_item")
@Data
@NoArgsConstructor
public class ScheduleItem implements Comparable<ScheduleItem> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_item_id")
    private long id;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "class_room_id")
    private ClassRoom classRoom;

    public ScheduleItem(LocalDate date, Group group, Teacher teacher, Course course) {
        this.date = date;
        this.group = group;
        this.teacher = teacher;
        this.course = course;
    }

    @Override
    public int compareTo(ScheduleItem o) {
        return getDate().compareTo(o.getDate());
    }
}
