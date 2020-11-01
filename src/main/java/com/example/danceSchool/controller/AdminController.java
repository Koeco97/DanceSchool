package com.example.danceSchool.controller;

import com.example.danceSchool.dto.AdminDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.service.AdminService;
import com.example.danceSchool.service.LessonService;
import com.example.danceSchool.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private final AdminService adminService;
    private final LessonService lessonService;
    private final TeacherService teacherService;


    @Autowired
    public AdminController(AdminService adminService, LessonService lessonService, TeacherService teacherService) {
        this.adminService = adminService;
        this.lessonService = lessonService;
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/{id}")
    public AdminDto findTeacherById(@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders) {
        return adminService.findAdminById(id);
    }

    @GetMapping
    public List<AdminDto> getAll() {
        return adminService.getAll();
    }

    @GetMapping("/map")
    public Map<Long, AdminDto> getMap() {
        return adminService.getAll().stream().collect(Collectors.toMap(AdminDto::getId, Function.identity()));
    }

    @PostMapping
    public AdminDto createAdmin(@RequestBody AdminDto adminDto) {
        return adminService.createAdmin(adminDto);
    }

    @PutMapping(value = "/{id}")
    public AdminDto updateAdmin(@PathVariable("id") Long id, @RequestBody AdminDto adminDto) {
        return adminService.updateAdmin(adminDto, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }

    @GetMapping(value = "/lesson/{id}")
    public LessonDto getLessonById(@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders) {
        return lessonService.getLessonById(id);
    }

    @GetMapping(value = "/lessons")
    public List<LessonDto> getAllLessons() {
        return lessonService.getAll();
    }

    @GetMapping(value = "/lessons/declined")
    public List<SheduleReport> getDeclinedLessons() {
        return adminService.getDeclinedLessons();
    }

    @PostMapping("/lessons/new")
    public LessonDto createLesson(@RequestBody LessonDto lessonDto) {
        return lessonService.createLesson(lessonDto);
    }

    @GetMapping("/lessons/new")
    public List<SheduleReport> getNewLessons() {
        return adminService.getNewLessons();
    }

    @PutMapping(value = "/lessons/{id}")
    public LessonDto updateLesson(@PathVariable("id") Long id, @RequestBody LessonDto lessonDto) {
        return lessonService.updateLesson(lessonDto, id);
    }

    @GetMapping("/shedule")
    public void sendShedule() {
        adminService.sendShedule();
    }

    @DeleteMapping(value = "/lessons/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }

    @PutMapping("/lessons/{lessonId}/redirect/{teacherId}")
    public LessonDto redirect(@PathVariable("lessonId") Long id, @PathVariable("teacherId") Long teacherId) {
        return adminService.redirect(id, teacherId);
    }

    @PutMapping(value = "/{id}/{role}")
    public void setRole(@PathVariable("id") Long id,
                        @PathVariable("role") String role) {
        adminService.setRole(id, role);
    }

    @DeleteMapping(value = "/teachers/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

}
