package com.example.danceSchool.converter;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LessonToLessonDtoConverter implements Converter<Lesson, LessonDto> {
    private final ConversionService conversionService;

    @Autowired
    public LessonToLessonDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public LessonDto convert(Lesson lesson) {
        LessonDto target = new LessonDto();
        target.setId(lesson.getId());
        target.setDate(lesson.getDate());
        target.setComment(lesson.getComment());
        target.setGroup(conversionService.convert(lesson.getGroup(), GroupDto.class));
        return target;
    }
}
