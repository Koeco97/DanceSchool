package com.example.danceSchool.converter;

import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LessonDtoToLessonConverter implements Converter<LessonDto, Lesson> {
    private final ConversionService conversionService;

    @Autowired
    public LessonDtoToLessonConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Lesson convert(LessonDto lessonDto) {
        Lesson target = new Lesson();
        target.setDate(lessonDto.getDate());
        target.setComment(lessonDto.getComment());
        target.setGroup(conversionService.convert(lessonDto.getGroup(), Group.class));
        return target;
    }
}
