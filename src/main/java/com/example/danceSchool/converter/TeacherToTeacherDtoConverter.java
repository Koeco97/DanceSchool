package com.example.danceSchool.converter;

import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Teacher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeacherToTeacherDtoConverter implements Converter<Teacher, TeacherDto> {
    @Override
    public TeacherDto convert(Teacher teacher) {
        TeacherDto target = new TeacherDto();
        target.setId(teacher.getId());
        target.setPersonId(teacher.getPersonId());
        return target;
    }
}
