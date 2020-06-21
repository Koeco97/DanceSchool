package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TeacherDto extends PersonDto {
    @JsonProperty(value = "group")
    private List<GroupDto> groups;

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }
}
