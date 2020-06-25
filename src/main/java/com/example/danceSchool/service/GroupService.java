package com.example.danceSchool.service;

import com.example.danceSchool.dto.GroupDto;

import java.util.List;

public interface GroupService {
    GroupDto findGroupById(Long id);

    GroupDto createGroup(GroupDto groupDto);

    GroupDto updateGroup(GroupDto groupDto, Long id);

    void deleteGroup(Long id);

    public List<GroupDto> getAll();
}
