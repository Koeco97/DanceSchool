package com.example.danceSchool.converter;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.TeacherDto;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.entity.Lesson;
import com.example.danceSchool.entity.Teacher;
import com.example.danceSchool.repository.GroupRepository;
import com.example.danceSchool.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Objects;

@Component
public class LessonDtoToLessonConverter implements Converter<LessonDto, Lesson> {
    private final ConversionService conversionService;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public LessonDtoToLessonConverter(ConversionService conversionService, GroupRepository groupRepository, TeacherRepository teacherRepository) {
        this.conversionService = conversionService;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Lesson convert(LessonDto lessonDto) {
        Lesson target = new Lesson();
        target.setBegin(lessonDto.getBegin());
        target.setEnd(lessonDto.getEnd());
        //get length in minutes
        Calendar begin = Calendar.getInstance();
        begin.setTime(lessonDto.getBegin());
        Calendar end = Calendar.getInstance();
        end.setTime(lessonDto.getEnd());
        int length = (int) (end.getTimeInMillis() - begin.getTimeInMillis()) / 1000 / 60;
        target.setLength(length);
        target.setStatus(lessonDto.getStatus());
        TeacherDto teacherDto = lessonDto.getTeacher();
        if (Objects.nonNull(teacherDto)) {
            Long teacherId = teacherDto.getId();
            Teacher teacher = Objects.isNull(teacherId) ?
                    conversionService.convert(teacherDto, Teacher.class) :
                    teacherRepository.getOne(teacherId);
            teacherRepository.save(teacher);
            target.setTeacher(teacher);
        }
        GroupDto groupDto = lessonDto.getGroup();
        if (Objects.nonNull(groupDto)) {
            Long groupId = groupDto.getId();
            Group group = Objects.isNull(groupId) ?
                    conversionService.convert(groupDto, Group.class) :
                    groupRepository.getOne(groupId);
            groupRepository.save(group);
            target.setGroup(group);
        }
        return target;
    }
}
