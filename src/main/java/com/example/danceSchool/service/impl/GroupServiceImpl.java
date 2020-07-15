package com.example.danceSchool.service.impl;

import com.example.danceSchool.dto.DanceDto;
import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.entity.Dance;
import com.example.danceSchool.entity.Group;
import com.example.danceSchool.exception.GroupException;
import com.example.danceSchool.repository.DanceRepository;
import com.example.danceSchool.repository.GroupRepository;
import com.example.danceSchool.repository.TeacherRepository;
import com.example.danceSchool.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final ConversionService conversionService;
    private final DanceRepository danceRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, ConversionService conversionService, TeacherRepository teacherRepository, DanceRepository danceRepository) {
        this.groupRepository = groupRepository;
        this.conversionService = conversionService;
        this.danceRepository = danceRepository;
    }

    @Override
    public GroupDto findGroupById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new GroupException("Group is not found"));
        return conversionService.convert(group, GroupDto.class);
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Group group = conversionService.convert(groupDto, Group.class);
        return conversionService.convert(groupRepository.save(group), GroupDto.class);
    }

    @Override
    public GroupDto updateGroup(GroupDto groupDto, Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new GroupException("Group is not found"));
        group.setGroupLevel(groupDto.getGroupLevel());
        DanceDto danceDto = groupDto.getDance();
        if (Objects.nonNull(danceDto)) {
            Long danceId = danceDto.getId();
            Dance dance = Objects.isNull(danceId) ?
                    conversionService.convert(danceDto, Dance.class) :
                    danceRepository.getOne(danceId);
            danceRepository.save(dance);
            group.setDance(dance);
        }
        return conversionService.convert(groupRepository.save(group), GroupDto.class);
    }

    @Override
    public void deleteGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new GroupException("Group is not found"));
        groupRepository.delete(group);
    }

    @Override
    public List<GroupDto> getAll() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().map(group -> conversionService.convert(group, GroupDto.class)).collect(Collectors.toList());
    }
}
