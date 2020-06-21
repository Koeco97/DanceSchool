package com.example.danceSchool.service;

import com.example.danceSchool.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    TeacherDto findTeacherById (Long id);
    TeacherDto createTeacher (TeacherDto teacherDto);
    TeacherDto updateTeacher (TeacherDto teacherDto);
    void deleteTeacher (Long id);
    public List<TeacherDto> getAll();
}
