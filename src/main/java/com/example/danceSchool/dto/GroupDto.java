package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

public class GroupDto implements Serializable {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "group_level")
    private Integer groupLevel;
    private TeacherDto teacher;
    private DanceDto dance;
    private List<ClientDto> clients;

    public int getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(int groupLevel) {
        this.groupLevel = groupLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public DanceDto getDance() {
        return dance;
    }

    public void setDance(DanceDto dance) {
        this.dance = dance;
    }
}
