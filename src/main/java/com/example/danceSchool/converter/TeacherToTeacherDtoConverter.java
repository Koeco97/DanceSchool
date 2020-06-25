package com.example.danceSchool.converter;

import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.example.danceSchool.converter.PersonToPersonDtoConverter.copyProperties;

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
        copyProperties(teacher, target);
        return target;
    }
}
