package com.example.danceSchool.service.impl;

import com.example.danceSchool.converter.CustomEntityConverter;
import com.example.danceSchool.dto.*;
import com.example.danceSchool.entity.*;
import com.example.danceSchool.exception.AdminException;
import com.example.danceSchool.exception.PersonException;
import com.example.danceSchool.exception.TeacherException;
import com.example.danceSchool.repository.*;
import com.example.danceSchool.service.AdminService;
import com.example.danceSchool.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final ConversionService conversionService;
    private final JdbcTemplate jdbcTemplate;
    private final EmailService emailService;
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;
    private final ClientRepository clientRepository;
    private final CustomEntityConverter customEntityConverter;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, ConversionService conversionService, JdbcTemplate jdbcTemplate, EmailService emailService, LessonRepository lessonRepository, TeacherRepository teacherRepository, RoleRepository roleRepository, PersonRepository personRepository, ClientRepository clientRepository, CustomEntityConverter customEntityConverter) {
        this.adminRepository = adminRepository;
        this.conversionService = conversionService;
        this.jdbcTemplate = jdbcTemplate;
        this.emailService = emailService;
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
        this.clientRepository = clientRepository;
        this.customEntityConverter = customEntityConverter;
    }

    @Override
    public AdminDto findAdminById(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new AdminException("Admin is not found"));
        return conversionService.convert(admin, AdminDto.class);
    }

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        Admin admin = conversionService.convert(adminDto, Admin.class);
        admin.setRole(roleRepository.findByName("ROLE_ADMIN"));
        return conversionService.convert(adminRepository.save(admin), AdminDto.class);
    }

    @Override
    public AdminDto updateAdmin(AdminDto adminDto, Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new TeacherException("Admin is not found"));
        admin.setFirstName(adminDto.getFirstName());
        admin.setSecondName(adminDto.getSecondName());
        admin.setLastName(adminDto.getLastName());
        admin.setBirthday(adminDto.getBirthday());
        admin.setSex(adminDto.getSex());
        admin.setEmail(adminDto.getEmail());
        admin.setPhoneNumber(adminDto.getPhoneNumber());
        admin.setLogin(adminDto.getLogin());
        admin.setPassword(adminDto.getPassword());
        return conversionService.convert(adminRepository.save(admin), AdminDto.class);
    }

    @Override
    public void deleteAdmin(Long id) {
        if (adminRepository.count() > 1) {
            Admin admin = adminRepository.findById(id).orElseThrow(() -> new TeacherException("Admin is not found"));
            adminRepository.delete(admin);
        } else {
            throw new AdminException("Impossible to delete last admin");
        }
    }

    @Override
    public List<AdminDto> getAll() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(admin -> conversionService.convert(admin, AdminDto.class)).collect(Collectors.toList());

    }

    @Override
    public void sendShedule() {
        Set<String> emails = new HashSet<>();
        String query = "SELECT e_mail FROM lesson, teacher, person " +
                "WHERE lesson.teacher_id = teacher.id AND teacher.id = person.id " +
                "AND lesson.status IS NULL";
        emails.addAll(jdbcTemplate.query(query, (resultSet, i) -> resultSet.getString(1)));
        System.out.println(emails);
        String subject = "danceSchool";
        String text = "На вас планируется назначить занятия. Просьба проверить и подтвердить/отклонить";
        for (String email : emails) {
            emailService.sendSimpleMessage(email, subject, text);
        }
    }

    @Override
    public LessonDto redirect(long lessonId, long teacherId) {
        Lesson lesson = lessonRepository.getOne(lessonId);
        lesson.setStatus(null);
        lesson.setTeacher(teacherRepository.getOne(teacherId));
        return conversionService.convert(lessonRepository.save(lesson), LessonDto.class);
    }

    @Override
    public List<SheduleReport> getDeclinedLessons() {
        List<SheduleReport> lessons = new ArrayList<>();
        String query = "SELECT lesson.id, `begin`, `end`, `length`, group_level, teacher_id, first_name, second_name, last_name, status, `name` FROM lesson left outer join `group` on lesson.group_id = `group`.id left outer join dance d on `group`.dance_id = d.id left outer join person on lesson.teacher_id=person.id WHERE status='declined'";
        lessons.addAll(jdbcTemplate.query(query, new SheduleRowMapper()));
        return lessons;
    }

    @Override
    public List<SheduleReport> getNewLessons() {
        List<SheduleReport> lessons = new ArrayList<>();
        String query = "SELECT lesson.id, `begin`, `end`, `length`, group_level, teacher_id, first_name, second_name, last_name, status, `name` FROM lesson left outer join `group` on lesson.group_id = `group`.id left outer join dance d on `group`.dance_id = d.id left outer join person on lesson.teacher_id=person.id WHERE status IS NULL";
        lessons.addAll(jdbcTemplate.query(query, new SheduleRowMapper()));
        return lessons;
    }

    public PersonDto setRole(Long personId, String role) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new PersonException("Person is not found"));
        String roleAfter = role;
        System.out.println(person.getRole().getName());
        String roleBefore = person.getRole().getName();
        if (roleBefore.equals("ROLE_CLIENT")) {
            if (roleAfter.equals("ROLE_TEACHER")) {
                Teacher teacher = (Teacher) customEntityConverter.convert(person, Teacher.class);
                teacher.setRole(roleRepository.findByName(roleAfter));
                teacherRepository.save(teacher);
                clientRepository.delete((Client) person);
                return conversionService.convert(teacher, PersonDto.class);
            } else if (roleAfter.equals("ROLE_ADMIN")) {
                Admin admin = conversionService.convert(person, Admin.class);
                admin.setRole(roleRepository.findByName(roleAfter));
                adminRepository.save(admin);
                clientRepository.delete((Client) person);
                return conversionService.convert(admin, PersonDto.class);
            }
        } else if (roleBefore.equals("ROLE_ADMIN")) {
            if (roleAfter.equals("ROLE_CLIENT")) {
                Client client = conversionService.convert(person, Client.class);
                client.setRole(roleRepository.findByName(roleAfter));
                clientRepository.save(client);
                adminRepository.delete((Admin) person);
                return conversionService.convert(client, PersonDto.class);
            } else if (roleAfter.equals("ROLE_TEACHER")) {
                Teacher teacher = conversionService.convert(person, Teacher.class);
                teacher.setRole(roleRepository.findByName(roleAfter));
                teacherRepository.save(teacher);
                adminRepository.delete((Admin) person);
                return conversionService.convert(teacher, PersonDto.class);
            }
        } else if (roleBefore.equals("ROLE_TEACHER")) {
            if (roleAfter.equals("ROLE_CLIENT")) {
                Client client = conversionService.convert(person, Client.class);
                client.setRole(roleRepository.findByName(roleAfter));
                clientRepository.save(client);
                teacherRepository.delete((Teacher) person);
                return conversionService.convert(client, PersonDto.class);
            } else if (roleAfter.equals("ROLE_ADMIN")) {
                Admin admin = conversionService.convert(person, Admin.class);
                admin.setRole(roleRepository.findByName(roleAfter));
                adminRepository.save(admin);
                teacherRepository.delete((Teacher) person);
                return conversionService.convert(admin, PersonDto.class);
            }
        }
        return conversionService.convert(personRepository.save(person), PersonDto.class);
    }
}


