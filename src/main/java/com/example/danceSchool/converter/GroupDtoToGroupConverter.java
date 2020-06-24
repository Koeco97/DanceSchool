package com.example.danceSchool.converter;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupDtoToGroupConverter implements Converter<GroupDto, Group> {
    private final ConversionService conversionService;

    @Autowired
    public GroupDtoToGroupConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Group convert(GroupDto groupDto) {
        Group target = new Group();
        target.setGroupLevel(groupDto.getGroupLevel());
        return target;
    }
}
