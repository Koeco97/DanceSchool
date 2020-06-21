package com.example.danceSchool.service.impl;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.entity.Teacher;
import com.example.danceSchool.exception.TeacherException;
import com.example.danceSchool.repository.TeacherRepository;
import com.example.danceSchool.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, ConversionService conversionService) {
        this.teacherRepository = teacherRepository;
        this.conversionService = conversionService;
    }

    @Override
    public TeacherDto findTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(()->new TeacherException("Teacher is not found"));
        return conversionService.convert(teacher, TeacherDto.class);
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = conversionService.convert(teacherDto, Teacher.class);
        return conversionService.convert(teacherRepository.save(teacher), TeacherDto.class);
    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherRepository.findById(teacherDto.getId()).orElseThrow(()->new TeacherException("Teacher is not found"));
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setSecondName(teacherDto.getSecondName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setBirthday(teacherDto.getBirthday());
        teacher.setSex(teacherDto.getSex());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setPhoneNumber(teacherDto.getPhoneNumber());
        teacher.setLogin(teacherDto.getLogin());
        teacher.setPassword(teacherDto.getPassword());
        List<Group> groups = new ArrayList<>();
        for (GroupDto groupDto : teacherDto.getGroups()) {
            groups.add(conversionService.convert(groupDto, Group.class));
        }
        teacher.setGroups(groups);
        return conversionService.convert(teacherRepository.save(teacher), TeacherDto.class);
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(()->new TeacherException("Teacher is not found"));
        teacherRepository.delete(teacher);
    }
    @Override
    public List<TeacherDto> getAll() {
        List <Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(teacher->conversionService.convert(teacher, TeacherDto.class)).collect(Collectors.toList());
    }
}
