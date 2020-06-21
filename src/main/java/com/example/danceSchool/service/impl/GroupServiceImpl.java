package com.example.danceSchool.service.impl;

import com.example.danceSchool.dto.ClientDto;
import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.entity.*;
import com.example.danceSchool.exception.GroupException;
import com.example.danceSchool.repository.GroupRepository;
import com.example.danceSchool.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final ConversionService conversionService;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, ConversionService conversionService) {
        this.groupRepository = groupRepository;
        this.conversionService = conversionService;
    }

    @Override
    public GroupDto findGroupById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(()->new GroupException("Group is not found"));
        return conversionService.convert(group,GroupDto.class);
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Group group = conversionService.convert(groupDto, Group.class);
        return conversionService.convert(groupRepository.save(group), GroupDto.class);
    }

    @Override
    public GroupDto updateGroup(GroupDto groupDto) {
        Group group = groupRepository.findById(groupDto.getId()).orElseThrow(()->new GroupException("Group is not found"));
        group.setGroupLevel(groupDto.getGroupLevel());
        group.setTeacher(conversionService.convert(groupDto.getTeacher(), Teacher.class));
        group.setDance(conversionService.convert(groupDto.getDance(), Dance.class));
        List<Client> clients = new ArrayList<>();
        for (ClientDto clientDto : groupDto.getClients()) {
            clients.add(conversionService.convert(clientDto, Client.class));
        }
        group.setClients(clients);
        List<Lesson> lessons = new ArrayList<>();
        for (LessonDto lessonDto : groupDto.getLessons()) {
            lessons.add(conversionService.convert(lessonDto, Lesson.class));
        }
        group.setLessons(lessons);
        return conversionService.convert(groupRepository.save(group), GroupDto.class);
    }

    @Override
    public void deleteGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(()->new GroupException("Group is not found"));
        groupRepository.delete(group);
    }
    @Override
    public List<GroupDto> getAll() {
        List <Group> groups = groupRepository.findAll();
        return groups.stream().map(group->conversionService.convert(group, GroupDto.class)).collect(Collectors.toList());
    }
}
