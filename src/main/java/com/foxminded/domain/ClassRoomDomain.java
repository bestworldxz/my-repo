package com.foxminded.domain;

import com.foxminded.model.ClassRoom;
import com.foxminded.service.ClassRoomService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ClassRoomDomain {
    private static final int MIN_NUMBER_OF_CLASS_ROOM = 10;
    private static final int MAX_NUMBER_OF_CLASS_ROOM = 150;
    private ClassRoomService classRoomService;

    public ClassRoomDomain(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    public List<ClassRoom> createClassRooms(int numberOfClassRooms) {
        List<ClassRoom> classRooms = new ArrayList<>();
        if (numberOfClassRooms <= 0) {
            throw new IllegalArgumentException("Class room cannot be zero or negative.");
        } else {
            List<Integer> classRoomsNumber = generateClassRoom(numberOfClassRooms);
            for (Integer classRoom : classRoomsNumber) {
                classRooms.add(classRoomService.createClassRoom(classRoom));
            }
        }
        return classRooms;
    }

    private List<Integer> generateClassRoom(int numberOfClassRooms) {
        List<Integer> result = new ArrayList<>();
        while (numberOfClassRooms > 0) {
            result.add(ThreadLocalRandom.current().nextInt(MIN_NUMBER_OF_CLASS_ROOM, MAX_NUMBER_OF_CLASS_ROOM));
            numberOfClassRooms--;
        }
        return result;
    }
}
