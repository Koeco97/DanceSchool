package com.example.danceSchool.service;

import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    TeacherDto findTeacherById(Long id);

    TeacherDto createTeacher(TeacherDto teacherDto);

    TeacherDto updateTeacher(TeacherDto teacherDto, Long id);

    void deleteTeacher(Long id);

    public List<TeacherDto> getAll();

    public List<SheduleReport> getLessons(String email);

    public LessonDto approve(long teacherId, long lessonId);

    public LessonDto decline(long teacherId, long lessonId);

    public void setStatus(List<SheduleReport> lessons);
}
