package com.example.danceSchool.controller;

import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

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
    public TeacherDto findTeacherById (@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders){
        return teacherService.findTeacherById(id);
    }
    @GetMapping
    public List<TeacherDto> getAll(){
        return teacherService.getAll();
    }
    @GetMapping("/map")
    public Map<Long, TeacherDto> getMap(){
        return teacherService.getAll().stream().collect(Collectors.toMap(TeacherDto::getId, Function.identity()));
    }
    @PostMapping
    public TeacherDto createTeacher (@RequestBody TeacherDto teacherDto){
        return teacherService.createTeacher(teacherDto);
    }
    @PutMapping
    public TeacherDto updateTeacher (TeacherDto teacherDto){
        return teacherService.updateTeacher(teacherDto);
    }
    @DeleteMapping
    public void deleteTeacher (Long id){
        teacherService.deleteTeacher(id);
    }
}
