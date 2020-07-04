package com.example.danceSchool.service.impl;

import com.example.danceSchool.dto.AdminDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.dto.SheduleRowMapper;
import com.example.danceSchool.entity.Admin;
import com.example.danceSchool.entity.Lesson;
import com.example.danceSchool.exception.TeacherException;
import com.example.danceSchool.repository.AdminRepository;
import com.example.danceSchool.repository.LessonRepository;
import com.example.danceSchool.repository.TeacherRepository;
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

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, ConversionService conversionService, JdbcTemplate jdbcTemplate, EmailService emailService, LessonRepository lessonRepository, TeacherRepository teacherRepository) {
        this.adminRepository = adminRepository;
        this.conversionService = conversionService;
        this.jdbcTemplate = jdbcTemplate;
        this.emailService = emailService;
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public AdminDto findAdminById(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new TeacherException("Admin is not found"));
        return conversionService.convert(admin, AdminDto.class);
    }

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        Admin admin = conversionService.convert(adminDto, Admin.class);
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
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new TeacherException("Admin is not found"));
        adminRepository.delete(admin);

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
}
