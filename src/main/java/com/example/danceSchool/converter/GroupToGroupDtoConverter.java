package com.example.danceSchool.converter;

import com.example.danceSchool.dto.DanceDto;
import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupToGroupDtoConverter implements Converter<Group, GroupDto> {
    private final ConversionService conversionService;

    @Autowired
    public GroupToGroupDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public GroupDto convert(Group group) {
        GroupDto target = new GroupDto();
        target.setId(group.getId());
        target.setGroupLevel(group.getGroupLevel());
        target.setTeacher(conversionService.convert(group.getTeacher(), TeacherDto.class));
        target.setDance(conversionService.convert(group.getDance(), DanceDto.class));
        return target;
    }
}
