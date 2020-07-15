package com.example.danceSchool.service.impl;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.entity.Lesson;
import com.example.danceSchool.entity.Teacher;
import com.example.danceSchool.exception.LessonException;
import com.example.danceSchool.repository.GroupRepository;
import com.example.danceSchool.repository.LessonRepository;
import com.example.danceSchool.repository.TeacherRepository;
import com.example.danceSchool.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final ConversionService conversionService;
    private final TeacherRepository teacherRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, GroupRepository groupRepository, ConversionService conversionService, TeacherRepository teacherRepository) {
        this.lessonRepository = lessonRepository;
        this.groupRepository = groupRepository;
        this.conversionService = conversionService;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public LessonDto getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new LessonException("Lesson is not found"));
        return conversionService.convert(lesson, LessonDto.class);
    }

    @Override
    public LessonDto createLesson(LessonDto lessonDto) {
        List<Lesson> lessons = lessonRepository.findAll();
        Date begin = lessonDto.getBegin();
        Date end = lessonDto.getEnd();
        boolean isBusy = false;
        for (Lesson lesson : lessons) {
            if ((begin.compareTo(lesson.getBegin()) >= 0) &&
                    (begin.compareTo(lesson.getEnd()) < 0)) {
                isBusy = true;
                break;
            } else if ((end.compareTo(lesson.getBegin()) > 0) &&
                    (end.compareTo(lesson.getEnd()) <= 0)) {
                isBusy = true;
                break;
            }
        }
        if (isBusy) {
            throw new LessonException("This time is busy");
        } else {
            Lesson lesson = conversionService.convert(lessonDto, Lesson.class);
            assert lesson != null;
            return conversionService.convert(lessonRepository.save(lesson), LessonDto.class);
        }
    }

    @Override
    public LessonDto updateLesson(LessonDto lessonDto, Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new LessonException("Lesson is not found"));
        lesson.setBegin(lessonDto.getBegin());
        lesson.setEnd(lessonDto.getEnd());
        lesson.setLength(lessonDto.getLength());
        lesson.setStatus(lessonDto.getStatus());
        TeacherDto teacherDto = lessonDto.getTeacher();
        if (Objects.nonNull(teacherDto)) {
            Long teacherId = teacherDto.getId();
            Teacher teacher = Objects.isNull(teacherId) ?
                    conversionService.convert(teacherDto, Teacher.class) :
                    teacherRepository.getOne(teacherId);
            teacherRepository.save(teacher);
            lesson.setTeacher(teacher);
        }
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
        if (Objects.nonNull(lesson.getStatus()) && lesson.getStatus().equals("approved")) {
            throw new LessonException("Lesson is approved");
        } else {
            lessonRepository.delete(lesson);
        }
    }

    @Override
    public List<LessonDto> getAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream().map(lesson -> conversionService.convert(lesson, LessonDto.class)).collect(Collectors.toList());
    }


}
