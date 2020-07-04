package com.example.danceSchool.converter;

import com.example.danceSchool.dto.DanceDto;
import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.entity.Dance;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.repository.DanceRepository;
import com.example.danceSchool.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GroupDtoToGroupConverter implements Converter<GroupDto, Group> {
    private final ConversionService conversionService;
    private final DanceRepository danceRepository;

    @Autowired
    public GroupDtoToGroupConverter(ConversionService conversionService, TeacherRepository teacherRepository, DanceRepository danceRepository) {
        this.conversionService = conversionService;
        this.danceRepository = danceRepository;
    }

    @Override
    public Group convert(GroupDto groupDto) {
        Group target = new Group();
        DanceDto danceDto = groupDto.getDance();
        if (Objects.nonNull(danceDto)) {
            Long danceId = danceDto.getId();
            Dance dance = Objects.isNull(danceId) ?
                    conversionService.convert(danceDto, Dance.class) :
                    danceRepository.getOne(danceId);
            danceRepository.save(dance);
            target.setDance(dance);
        }
        target.setGroupLevel(groupDto.getGroupLevel());
        return target;
    }
}
