package com.example.danceSchool.controller;

import com.example.danceSchool.dto.AdminDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.service.AdminService;
import com.example.danceSchool.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private final AdminService adminService;
    private final LessonService lessonService;


    @Autowired
    public AdminController(AdminService adminService, LessonService lessonService) {
        this.adminService = adminService;
        this.lessonService = lessonService;
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

    @PostMapping("/lesson")
    public LessonDto createLesson(@RequestBody LessonDto lessonDto) {
        return lessonService.createLesson(lessonDto);
    }

    @PutMapping(value = "/lesson/{id}")
    public LessonDto updateLesson(@PathVariable("id") Long id, @RequestBody LessonDto lessonDto) {
        return lessonService.updateLesson(lessonDto, id);
    }

    @GetMapping("/shedule")
    public void sendShedule() {
        adminService.sendShedule();
    }

    @DeleteMapping(value = "/lesson/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }

    @PutMapping("/lesson/{lessonId}/redirect/{teacherId}")
    public LessonDto redirect(@PathVariable("lessonId") Long id, @PathVariable("teacherId") Long teacherId) {
        return adminService.redirect(id, teacherId);
    }


}
