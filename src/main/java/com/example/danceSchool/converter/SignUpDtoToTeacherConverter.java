package com.example.danceSchool.converter;

import com.example.danceSchool.dto.SignUpDto;
import com.example.danceSchool.entity.Role;
import com.example.danceSchool.entity.Teacher;
import com.example.danceSchool.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SignUpDtoToTeacherConverter implements Converter<SignUpDto, Teacher> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public SignUpDtoToTeacherConverter(PasswordEncoder passwordEncoder, ConversionService conversionService, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public Teacher convert(SignUpDto signUpDto) {
        Teacher teacher = new Teacher();
        teacher.setEmail(signUpDto.getEmail());
        teacher.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        teacher.setRole(role);
        return teacher;
    }
}
