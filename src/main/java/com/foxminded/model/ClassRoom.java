package com.foxminded.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "class_room")
@Data
@NoArgsConstructor
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_room_id")
    private long classRoomId;

    @Column(name = "class_room")
    private Integer classRoom;

    @OneToMany(mappedBy = "classRoom")
    @JsonIgnore
    private List<ScheduleItem> scheduleItem;

    public ClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }

}
