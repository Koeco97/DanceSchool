package com.example.danceSchool.service;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.dto.LessonDto;

import java.util.List;

public interface LessonService {
    LessonDto getLessonById(Long id);
    LessonDto createLesson(LessonDto lessonDto);
    LessonDto updateLesson(LessonDto lessonDto);
    void deleteLesson(Long id);
    public List<LessonDto> getAll();
}
