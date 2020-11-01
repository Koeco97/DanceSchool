package com.example.danceSchool.security;

import com.example.danceSchool.dto.SignUpDto;
import com.example.danceSchool.entity.Admin;
import com.example.danceSchool.entity.Client;
import com.example.danceSchool.exception.PersonException;
import com.example.danceSchool.repository.AdminRepository;
import com.example.danceSchool.repository.ClientRepository;
import com.example.danceSchool.repository.PersonRepository;
import com.example.danceSchool.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@CrossOrigin
@Transactional
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final ClientRepository clientRepository;
    private final ConversionService conversionService;
    private final PersonRepository personRepository;
    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public JwtAuthenticationController(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            @Qualifier("customUserDetailsService") UserDetailsService userDetailsService,
            ClientRepository clientRepository,
            ConversionService conversionService, PersonRepository personRepository, AdminRepository adminRepository, TeacherRepository teacherRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.clientRepository = clientRepository;
        this.conversionService = conversionService;
        this.personRepository = personRepository;
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity<?> signUpAsClient(@RequestBody SignUpDto signUpDto) {
        if (personRepository.count() == 0) {
            return createAdmin(signUpDto);
        } else if (Objects.isNull(personRepository.findByEmail(signUpDto.getEmail()))) {
            Client client = conversionService.convert(signUpDto, Client.class);
            assert client != null;
            clientRepository.save(client);
            return ResponseEntity.ok("");
        } else {
            throw new PersonException("user with this email already exists");
        }
    }

    public ResponseEntity<?> createAdmin(SignUpDto signUpDto) {
        Admin admin = conversionService.convert(signUpDto, Admin.class);
        assert admin != null;
        adminRepository.save(admin);
        return ResponseEntity.ok("");
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
