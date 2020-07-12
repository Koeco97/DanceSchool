package com.example.danceSchool.service;

import com.example.danceSchool.dto.AdminDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.dto.SheduleRowMapper;
import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Admin;
import com.example.danceSchool.entity.Lesson;
import com.example.danceSchool.entity.Teacher;
import com.example.danceSchool.exception.AdminException;
import com.example.danceSchool.exception.TeacherException;
import com.example.danceSchool.repository.AdminRepository;
import com.example.danceSchool.repository.LessonRepository;
import com.example.danceSchool.repository.RoleRepository;
import com.example.danceSchool.repository.TeacherRepository;
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

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, ConversionService conversionService, JdbcTemplate jdbcTemplate, EmailService emailService, LessonRepository lessonRepository, TeacherRepository teacherRepository, RoleRepository roleRepository) {
        this.adminRepository = adminRepository;
        this.conversionService = conversionService;
        this.jdbcTemplate = jdbcTemplate;
        this.emailService = emailService;
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AdminDto findAdminById(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new TeacherException("Admin is not found"));
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

    public List<TeacherDto> getTeachersWithoutRole() {
        List<TeacherDto> teachers = new ArrayList<>();
        String query = "SELECT person.id, first_name, second_name, last_name, birthday, sex, e_mail, phone_number, name FROM person left outer join `role` on person.role_id = role.id WHERE `name` = 'ROLE_USER'";
        teachers.addAll(jdbcTemplate.query(query, new Object[]{}, (resultSet, i) -> {
            TeacherDto teacherDto = new TeacherDto();
            teacherDto.setId(resultSet.getLong("id"));
            teacherDto.setFirstName(resultSet.getString("first_name"));
            teacherDto.setSecondName(resultSet.getString("second_name"));
            teacherDto.setLastName(resultSet.getString("last_name"));
            teacherDto.setSex(resultSet.getString("sex"));
            teacherDto.setEmail(resultSet.getString("e_mail"));
            teacherDto.setPhoneNumber(resultSet.getString("phone_number"));
            teacherDto.setBirthday(resultSet.getDate("birthday"));
            return teacherDto;
        }));
        return teachers;
    }

    public TeacherDto giveTeacherRole(Long teacherId) {
        Teacher teacher = teacherRepository.getOne(teacherId);
        teacher.setRole(roleRepository.findByName("ROLE_TEACHER"));
        return conversionService.convert(teacherRepository.save(teacher), TeacherDto.class);
    }
}
