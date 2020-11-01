package com.example.danceSchool.service.impl;

import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.dto.SheduleRowMapper;
import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Lesson;
import com.example.danceSchool.entity.Teacher;
import com.example.danceSchool.exception.LessonException;
import com.example.danceSchool.exception.TeacherException;
import com.example.danceSchool.repository.DanceRepository;
import com.example.danceSchool.repository.GroupRepository;
import com.example.danceSchool.repository.LessonRepository;
import com.example.danceSchool.repository.TeacherRepository;
import com.example.danceSchool.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ConversionService conversionService;
    private final JdbcTemplate jdbcTemplate;
    private final GroupRepository groupRepository;
    private final LessonRepository lessonRepository;
    private final DanceRepository danceRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, ConversionService conversionService, JdbcTemplate jdbcTemplate, GroupRepository groupRepository, LessonRepository lessonRepository, DanceRepository danceRepository) {
        this.teacherRepository = teacherRepository;
        this.conversionService = conversionService;
        this.jdbcTemplate = jdbcTemplate;
        this.groupRepository = groupRepository;
        this.lessonRepository = lessonRepository;
        this.danceRepository = danceRepository;
    }

    @Override
    public TeacherDto findTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherException("Teacher is not found"));
        return conversionService.convert(teacher, TeacherDto.class);
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = conversionService.convert(teacherDto, Teacher.class);
        return conversionService.convert(teacherRepository.save(teacher), TeacherDto.class);
    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto, Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherException("Teacher is not found"));
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setSecondName(teacherDto.getSecondName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setBirthday(teacherDto.getBirthday());
        teacher.setSex(teacherDto.getSex());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setPhoneNumber(teacherDto.getPhoneNumber());
        teacher.setLogin(teacherDto.getLogin());
        return conversionService.convert(teacherRepository.save(teacher), TeacherDto.class);
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherException("Teacher is not found"));
        teacherRepository.delete(teacher);
    }

    @Override
    public List<TeacherDto> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(teacher -> conversionService.convert(teacher, TeacherDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<SheduleReport> getLessons(String email) {
        Teacher teacher = teacherRepository.findByEmail(email);
        List<SheduleReport> lessons = new ArrayList<>();
        String query = "SELECT lesson.id, `begin`, `end`, `length`, group_level, teacher_id, first_name, second_name, last_name, status, `name` FROM lesson left outer join `group` on lesson.group_id = `group`.id left outer join dance d on `group`.dance_id = d.id left outer join person on lesson.teacher_id=person.id WHERE status IS NULL AND teacher_id=?";
        lessons.addAll(jdbcTemplate.query(query, new Object[]{teacher.getId()}, new SheduleRowMapper()));
        return lessons;
    }

    public List<SheduleReport> getShedule(String email) {
        Teacher teacher = teacherRepository.findByEmail(email);
        List<SheduleReport> lessons = new ArrayList<>();
        String query = "SELECT lesson.id, `begin`, `end`, `length`, group_level, teacher_id, first_name, second_name, last_name, status, `name` FROM lesson left outer join `group` on lesson.group_id = `group`.id left outer join dance d on `group`.dance_id = d.id left outer join person on lesson.teacher_id=person.id WHERE teacher_id=?";
        lessons.addAll(jdbcTemplate.query(query, new Object[]{teacher.getId()}, new SheduleRowMapper()));
        return lessons;
    }

    @Override
    public LessonDto approve(long teacherId, long lessonId) {
        Lesson lesson = lessonRepository.getOne(lessonId);
        if (lesson.getTeacher().getId() == teacherId) {
            lesson.setStatus("approved");
        }
        return conversionService.convert(lessonRepository.save(lesson), LessonDto.class);
    }

    @Override
    public LessonDto decline(long teacherId, long lessonId) {
        Lesson lesson = lessonRepository.getOne(lessonId);
        if (lesson.getTeacher().getId() == teacherId) {
            lesson.setStatus("declined");
        }
        return conversionService.convert(lessonRepository.save(lesson), LessonDto.class);
    }

    @Override
    public void setStatus(List<SheduleReport> lessons) {
        for (SheduleReport row : lessons) {
            Lesson lesson = lessonRepository.findById(row.getId()).orElseThrow(() -> new LessonException("Lesson is not found"));
            lesson.setStatus(row.getStatus());
            lessonRepository.save(lesson);
        }
    }


}
