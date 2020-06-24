package com.example.danceSchool.converter;

import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeacherDtoToTeacherConverter implements Converter<TeacherDto, Teacher> {
    private final ConversionService conversionService;

    @Autowired
    public TeacherDtoToTeacherConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Teacher convert(TeacherDto teacherDto) {
        Teacher target = new Teacher();
        target.setFirstName(teacherDto.getFirstName());
        target.setSecondName(teacherDto.getSecondName());
        target.setLastName(teacherDto.getLastName());
        target.setBirthday(teacherDto.getBirthday());
        target.setSex(teacherDto.getSex());
        target.setEmail(teacherDto.getEmail());
        target.setPhoneNumber(teacherDto.getPhoneNumber());
        target.setLogin(teacherDto.getLogin());
        target.setPassword(teacherDto.getPassword());
        return target;
    }
}
