package com.example.danceSchool.converter;

import com.example.danceSchool.dto.AdminDto;
import com.example.danceSchool.entity.Admin;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.example.danceSchool.converter.PersonDtoToPersonConverter.copyProperties;

@Component
public class AdminDtoToAdminConverter implements Converter<AdminDto, Admin> {
    @Override
    public Admin convert(AdminDto adminDto) {
        Admin target = new Admin();
        copyProperties(adminDto, target);
        return target;
    }
}
