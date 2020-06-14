package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

public class GroupDto extends baseDto{
    @JsonProperty(value = "group_level")
    private Integer groupLevel;
    @JsonProperty(value = "teacher")
    private TeacherDto teacher;
    @JsonProperty(value = "dance")
    private DanceDto dance;

    public int getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(int groupLevel) {
        this.groupLevel = groupLevel;
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