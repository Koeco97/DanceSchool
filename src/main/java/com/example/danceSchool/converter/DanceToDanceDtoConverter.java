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
public class DanceToDanceDtoConverter implements Converter<Dance, DanceDto> {
    private final ConversionService conversionService;

    @Autowired
    public DanceToDanceDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public DanceDto convert(Dance dance) {
        DanceDto target = new DanceDto();
        target.setId(dance.getId());
        target.setName(dance.getName());
        List<GroupDto> groups = new ArrayList<>();
        for (Group group : dance.getGroups()) {
            groups.add(conversionService.convert(group, GroupDto.class));
        }
        target.setGroups(groups);
        return target;
    }
}
