package com.example.danceSchool.converter;

import com.example.danceSchool.dto.DanceDto;
import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.entity.Dance;
import com.example.danceSchool.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DanceDtoToDanceConverter implements Converter<DanceDto, Dance> {
    private final ConversionService conversionService;

    @Autowired
    public DanceDtoToDanceConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Dance convert(DanceDto danceDto) {
        Dance target = new Dance();
        target.setName(danceDto.getName());
        List<Group> groups = new ArrayList<>();
        for (GroupDto groupDto : danceDto.getGroups()) {
        groups.add(conversionService.convert(groupDto, Group.class));
        }
        target.setGroups(groups);
        return target;
    }
}
