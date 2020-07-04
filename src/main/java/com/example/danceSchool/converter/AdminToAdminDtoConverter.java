package com.example.danceSchool.converter;

import com.example.danceSchool.dto.AdminDto;
import com.example.danceSchool.entity.Admin;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.example.danceSchool.converter.PersonToPersonDtoConverter.copyProperties;

@Component
public class AdminToAdminDtoConverter implements Converter<Admin, AdminDto> {

    @Override
    public AdminDto convert(Admin admin) {
        AdminDto target = new AdminDto();
        copyProperties(admin, target);
        return target;
    }
}