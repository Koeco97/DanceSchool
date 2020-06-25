package com.example.danceSchool.controller;

import com.example.danceSchool.dto.GroupDto;
import com.example.danceSchool.service.GroupService;
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
@RequestMapping(value = "/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(value = "/{id}")
    public GroupDto findGroupById(@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders) {
        return groupService.findGroupById(id);
    }

    @GetMapping
    public List<GroupDto> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/map")
    public Map<Long, GroupDto> getMap() {
        return groupService.getAll().stream().collect(Collectors.toMap(GroupDto::getId, Function.identity()));
    }

    @PostMapping
    public GroupDto createGroup(@RequestBody GroupDto groupDto) {
        return groupService.createGroup(groupDto);
    }

    @PutMapping(value = "/{id}")
    public GroupDto updateGroup(@PathVariable("id") Long id, @RequestBody GroupDto groupDto) {
        return groupService.updateGroup(groupDto, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
}
