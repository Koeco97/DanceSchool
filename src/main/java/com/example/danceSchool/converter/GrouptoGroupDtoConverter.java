package com.example.danceSchool.converter;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.entity.Group;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GrouptoGroupDtoConverter implements Converter<Group, GroupDto> {
    @Override
    public GroupDto convert(Group group) {
        GroupDto target = new GroupDto();
        target.setId(group.getId());
        target.setGroupLevel(group.getGroupLevel());
        target.setDanceId(group.getDanceId());
        target.setTeacherId(group.getTeacherId());
        return target;
    }
}
