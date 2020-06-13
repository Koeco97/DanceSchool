package com.example.danceSchool.converter;

import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Teacher;
import org.hibernate.annotations.Cache;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeacherDtoToTeacherConverter implements Converter<TeacherDto, Teacher> {

    @Override
    public Teacher convert(TeacherDto teacherDto) {
        Teacher target = new Teacher();
        target.setPersonId(teacherDto.getPersonId());
        return target;
    }
}
