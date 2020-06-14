package com.example.danceSchool.converter;

import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.entity.Lesson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LessonDtoToLessonConverter implements Converter<LessonDto, Lesson> {

    @Override
    public Lesson convert(LessonDto lessonDto) {
        Lesson target = new Lesson();
        target.setDate(lessonDto.getDate());
        target.setComment(lessonDto.getComment());
        return target;
    }
}
