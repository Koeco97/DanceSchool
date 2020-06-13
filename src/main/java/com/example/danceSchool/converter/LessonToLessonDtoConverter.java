package com.example.danceSchool.converter;

import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.PersonDto;
import com.example.danceSchool.entity.Lesson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LessonToLessonDtoConverter implements Converter<Lesson, LessonDto> {

    @Override
    public LessonDto convert(Lesson lesson) {
        LessonDto target = new LessonDto();
        target.setId(lesson.getId());
        target.setDate(lesson.getDate());
        target.setGroup_id(lesson.getGroup_id());
        target.setComment(lesson.getComment());
        return target;
    }
}
