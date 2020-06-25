package com.example.danceSchool.converter;

import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Teacher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.example.danceSchool.converter.PersonDtoToPersonConverter.copyProperties;


@Component
public class TeacherDtoToTeacherConverter implements Converter<TeacherDto, Teacher> {

    @Override
    public Teacher convert(TeacherDto teacherDto) {
        Teacher target = new Teacher();
        copyProperties(teacherDto, target);
        return target;
    }
}
