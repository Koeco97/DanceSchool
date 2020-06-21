package com.example.danceSchool.converter;

import com.example.danceSchool.dto.*;
import com.example.danceSchool.entity.Client;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupToGroupDtoConverter implements Converter<Group, GroupDto> {
    private final ConversionService conversionService;

    @Autowired
    public GroupToGroupDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public GroupDto convert(Group group) {
        GroupDto target = new GroupDto();
        target.setId(group.getId());
        target.setGroupLevel(group.getGroupLevel());
        target.setTeacher(conversionService.convert(group.getTeacher(), TeacherDto.class));
        target.setDance(conversionService.convert(group.getDance(), DanceDto.class));
        List<ClientDto> clients = new ArrayList<>();
        for (Client client : group.getClients()) {
            clients.add(conversionService.convert(client, ClientDto.class));
        }
        target.setClients(clients);
        List<LessonDto> lessons = new ArrayList<>();
        for (Lesson lesson : group.getLessons()) {
            lessons.add(conversionService.convert(lesson, LessonDto.class));
        }
        target.setLessons(lessons);
        return target;
    }
}
