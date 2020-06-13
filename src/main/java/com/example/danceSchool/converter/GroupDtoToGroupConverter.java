package com.example.danceSchool.converter;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.entity.Group;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupDtoToGroupConverter implements Converter<GroupDto, Group> {

    @Override
    public Group convert(GroupDto groupDto) {
        Group target = new Group();
        target.setGroupLevel(groupDto.getGroupLevel());
        target.setDanceId(groupDto.getDanceId());
        target.setTeacherId(groupDto.getTeacherId());
        return target;
    }
}
