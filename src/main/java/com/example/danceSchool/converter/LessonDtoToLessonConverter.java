package com.example.danceSchool.converter;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.entity.Lesson;
import com.example.danceSchool.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LessonDtoToLessonConverter implements Converter<LessonDto, Lesson> {
    private final ConversionService conversionService;
    private final GroupRepository groupRepository;

    @Autowired
    public LessonDtoToLessonConverter(ConversionService conversionService, GroupRepository groupRepository) {
        this.conversionService = conversionService;
        this.groupRepository = groupRepository;
    }

    @Override
    public Lesson convert(LessonDto lessonDto) {
        Lesson target = new Lesson();
        target.setDate(lessonDto.getDate());
        target.setComment(lessonDto.getComment());
        GroupDto groupDto = lessonDto.getGroup();
        if (Objects.nonNull(groupDto)) {
            Long groupId = groupDto.getId();
            Group group = Objects.isNull(groupId) ?
                    conversionService.convert(groupDto, Group.class) :
                    groupRepository.getOne(groupId);
            groupRepository.save(group);
            target.setGroup(group);
        }
        return target;
    }
}
