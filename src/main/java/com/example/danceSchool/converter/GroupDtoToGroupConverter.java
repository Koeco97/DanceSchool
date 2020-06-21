package com.example.danceSchool.converter;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupDtoToGroupConverter implements Converter<GroupDto, Group> {
    private final ConversionService conversionService;

    @Autowired
    public GroupDtoToGroupConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Group convert(GroupDto groupDto) {
        Group target = new Group();
        target.setGroupLevel(groupDto.getGroupLevel());
        target.setTeacher(conversionService.convert(groupDto.getTeacher(), Teacher.class));
        target.setDance(conversionService.convert(groupDto.getDance(), Dance.class));
        List<Client> clients = new ArrayList<>();
        for (ClientDto clientDto : groupDto.getClients()) {
            clients.add(conversionService.convert(clientDto, Client.class));
        }
        target.setClients(clients);
        List<Lesson> lessons = new ArrayList<>();
        for (LessonDto lessonDto : groupDto.getLessons()) {
            lessons.add(conversionService.convert(lessonDto, Lesson.class));
        }
        target.setLessons(lessons);
        return target;
    }
}
