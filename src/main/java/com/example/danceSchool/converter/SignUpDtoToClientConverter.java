package com.example.danceSchool.converter;


import com.example.danceSchool.dto.SignUpDto;
import com.example.danceSchool.entity.Client;
import com.example.danceSchool.entity.Role;
import com.example.danceSchool.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SignUpDtoToClientConverter implements Converter<SignUpDto, Client> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public SignUpDtoToClientConverter(PasswordEncoder passwordEncoder, ConversionService conversionService, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public Client convert(SignUpDto signUpDto) {
        Client client = new Client();
        client.setEmail(signUpDto.getEmail());
        client.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_CLIENT");
        client.setRole(role);
        return client;
    }
}