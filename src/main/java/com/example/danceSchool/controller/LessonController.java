package com.example.danceSchool.controller;

import com.example.danceSchool.dto.LessonDto;
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
@RequestMapping(value = "/lessons")
public class LessonController {
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping(value = "/{id}")
    public LessonDto getLessonById(@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders) {
        return lessonService.getLessonById(id);
    }

    @GetMapping
    public List<LessonDto> getAll() {
        return lessonService.getAll();
    }

    @GetMapping("/map")
    public Map<Long, LessonDto> getMap() {
        return lessonService.getAll().stream().collect(Collectors.toMap(LessonDto::getId, Function.identity()));
    }

    @PostMapping
    public LessonDto createLesson(@RequestBody LessonDto lessonDto) {
        return lessonService.createLesson(lessonDto);
    }

    @PutMapping(value = "/{id}")
    public LessonDto updateLesson(@PathVariable("id") Long id, @RequestBody LessonDto lessonDto) {
        return lessonService.updateLesson(lessonDto, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }
}
