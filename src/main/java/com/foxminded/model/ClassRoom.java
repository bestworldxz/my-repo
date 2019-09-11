package com.foxminded.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "class_room")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_room_id")
    private long classRoomId;

    @Column(name = "class_room")
    private Integer classRoom;

    @OneToMany(mappedBy = "classRoom")
    private List<ScheduleItem> scheduleItem;

    public ClassRoom() {

    }

    public ClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }

    public long getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(long classRoomId) {
        this.classRoomId = classRoomId;
    }

    public int getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }

    public void setClassRoom(Integer classRoom) {
        this.classRoom = classRoom;
    }

    public List<ScheduleItem> getScheduleItem() {
        return scheduleItem;
    }

    public void setScheduleItem(List<ScheduleItem> scheduleItem) {
        this.scheduleItem = scheduleItem;
    }
}
