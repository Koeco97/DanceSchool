package com.example.danceSchool.service;

import com.example.danceSchool.dto.AdminDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.dto.TeacherDto;

import java.util.List;

public interface AdminService {
    AdminDto findAdminById(Long id);

    AdminDto createAdmin(AdminDto adminDto);

    AdminDto updateAdmin(AdminDto adminDto, Long id);

    void deleteAdmin(Long id);

    public List<AdminDto> getAll();

    public void sendShedule();

    public LessonDto redirect(long lessonId, long teacherId);

    public List<SheduleReport> getDeclinedLessons();

    public List<TeacherDto> getTeachersWithoutRole();

    public TeacherDto giveTeacherRole(Long teacherId);
}
