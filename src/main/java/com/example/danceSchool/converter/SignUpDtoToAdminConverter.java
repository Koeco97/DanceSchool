package com.example.danceSchool.converter;

import com.example.danceSchool.dto.SignUpDto;
import com.example.danceSchool.entity.Admin;
import com.example.danceSchool.entity.Role;
import com.example.danceSchool.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SignUpDtoToAdminConverter implements Converter<SignUpDto, Admin> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public SignUpDtoToAdminConverter(PasswordEncoder passwordEncoder, ConversionService conversionService, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public Admin convert(SignUpDto signUpDto) {
        Admin admin = new Admin();
        admin.setEmail(signUpDto.getEmail());
        admin.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        admin.setRole(role);
        return admin;
    }
}