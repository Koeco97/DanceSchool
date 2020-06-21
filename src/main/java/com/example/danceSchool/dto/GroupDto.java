package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GroupDto extends baseDto{
    @JsonProperty(value = "group_level")
    private Integer groupLevel;
    @JsonProperty(value = "teacher")
    private TeacherDto teacher;
    @JsonProperty(value = "dance")
    private DanceDto dance;
    @JsonProperty(value = "clients")
    private List<ClientDto> clients;
    @JsonProperty(value = "lessons")
    private List<LessonDto> lessons;

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


    public List<ClientDto> getClients() {
        return clients;
    }

    public void setClients(List<ClientDto> clients) {
        this.clients = clients;
    }

    public List<LessonDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDto> lessons) {
        this.lessons = lessons;
    }
}
