package com.foxminded.domain;

import com.foxminded.exception.EntityNotFoundException;
import com.foxminded.model.Group;
import com.foxminded.service.GroupService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GroupDomain {

    private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    private static final String NUMERIC_STRING = "0123456789";

    private GroupService groupService;

    public GroupDomain(GroupService groupService) {
        this.groupService = groupService;
    }

    public List<Group> createGroup(int numberOfGroups) throws EntityNotFoundException{
        List<Group> groups = new ArrayList<>();
        if (numberOfGroups < 0) {
            throw new IllegalArgumentException();
        }
        List<String> groupNames = generateGroupNames(numberOfGroups);
        for (String groupName : groupNames) {
            groups.add(groupService.createGroup(groupName));
        }
        return groups;
    }

    private List<String> generateGroupNames(int groupsNumber) {
        List<String> randomGroupName = new ArrayList<>();
        for (int i = 0; i < groupsNumber; i++) {
            randomGroupName.add(randomGroupName());
        }
        return randomGroupName;
    }

    private String randomGroupName() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (i < 2) {
                int charIndex = ThreadLocalRandom.current().nextInt(ALPHA_STRING.length());
                stringBuilder.append(ALPHA_STRING.charAt(charIndex));
            } else if (i == 2) {
                stringBuilder.append("-");
            } else {
                int charIndex = ThreadLocalRandom.current().nextInt(NUMERIC_STRING.length());
                stringBuilder.append(NUMERIC_STRING.charAt(charIndex));
            }
        }
        return stringBuilder.toString();
    }
}
