package com.example.danceSchool.service.impl;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.entity.Lesson;
import com.example.danceSchool.exception.LessonException;
import com.example.danceSchool.repository.GroupRepository;
import com.example.danceSchool.repository.LessonRepository;
import com.example.danceSchool.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final ConversionService conversionService;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, GroupRepository groupRepository, ConversionService conversionService) {
        this.lessonRepository = lessonRepository;
        this.groupRepository = groupRepository;
        this.conversionService = conversionService;
    }

    @Override
    public LessonDto getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new LessonException("Lesson is not found"));
        return conversionService.convert(lesson, LessonDto.class);
    }

    @Override
    public LessonDto createLesson(LessonDto lessonDto) {
        Lesson lesson = conversionService.convert(lessonDto, Lesson.class);
        return conversionService.convert(lessonRepository.save(lesson), LessonDto.class);
    }

    @Override
    public LessonDto updateLesson(LessonDto lessonDto, Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new LessonException("Lesson is not found"));
        lesson.setDate(lessonDto.getDate());
        lesson.setComment(lessonDto.getComment());
        GroupDto groupDto = lessonDto.getGroup();
        if (Objects.nonNull(groupDto)) {
            Long groupId = groupDto.getId();
            Group group = Objects.isNull(groupId) ?
                    conversionService.convert(groupDto, Group.class) :
                    groupRepository.getOne(groupId);
            groupRepository.save(group);
            lesson.setGroup(group);
        }
        return conversionService.convert(lessonRepository.save(lesson), LessonDto.class);
    }

    @Override
    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new LessonException("Lesson is not found"));
        lessonRepository.delete(lesson);
    }

    @Override
    public List<LessonDto> getAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream().map(lesson -> conversionService.convert(lesson, LessonDto.class)).collect(Collectors.toList());
    }
}
