package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DanceDto extends baseDto {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "groups")
    private List<GroupDto> groups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }
}
