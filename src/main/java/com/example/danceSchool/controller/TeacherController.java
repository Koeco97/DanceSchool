package com.example.danceSchool.controller;

import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.SheduleReport;
import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.service.TeacherService;
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
@RequestMapping(value = "/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/{id}")
    public TeacherDto findTeacherById(@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders) {
        return teacherService.findTeacherById(id);
    }

    @GetMapping
    public List<TeacherDto> getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/map")
    public Map<Long, TeacherDto> getMap() {
        return teacherService.getAll().stream().collect(Collectors.toMap(TeacherDto::getId, Function.identity()));
    }

    @PostMapping
    public TeacherDto createTeacher(@RequestBody TeacherDto teacherDto) {
        return teacherService.createTeacher(teacherDto);
    }

    @PutMapping(value = "/{id}")
    public TeacherDto updateTeacher(@PathVariable("id") Long id, @RequestBody TeacherDto teacherDto) {
        return teacherService.updateTeacher(teacherDto, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

    @GetMapping("/{id}/lessons")
    public List<SheduleReport> getLessons(@PathVariable Long id) {
        return teacherService.getLessons(id);
    }

    @PutMapping("/{id}/approve/{lessonId}")
    public LessonDto approve(@PathVariable("id") Long id, @PathVariable("lessonId") Long lessonId) {
        return teacherService.approve(id, lessonId);
    }

    @PutMapping("/{id}/decline/{lessonId}")
    public LessonDto decline(@PathVariable("id") Long id, @PathVariable("lessonId") Long lessonId) {
        return teacherService.decline(id, lessonId);
    }
}
