package com.example.danceSchool.converter;

import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeacherToTeacherDtoConverter implements Converter<Teacher, TeacherDto> {
    private final ConversionService conversionService;

    @Autowired
    public TeacherToTeacherDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public TeacherDto convert(Teacher teacher) {
        TeacherDto target = new TeacherDto();
        target.setId(teacher.getId());
        target.setFirstName(teacher.getFirstName());
        target.setSecondName(teacher.getSecondName());
        target.setLastName(teacher.getLastName());
        target.setBirthday(teacher.getBirthday());
        target.setSex(teacher.getSex());
        target.setEmail(teacher.getEmail());
        target.setPhoneNumber(teacher.getPhoneNumber());
        target.setLogin(teacher.getLogin());
        target.setPassword(teacher.getPassword());
        return target;
    }
}
