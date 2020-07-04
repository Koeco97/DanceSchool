package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SheduleReport {

    @JsonProperty(value = "id")
    Long id;

    ;
    @JsonProperty(value = "begin")
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm")
    private Date begin;
    @JsonProperty(value = "end")
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm")
    private Date end;
    @JsonProperty(value = "length")
    private int length;
    @JsonProperty(value = "groupLevel")
    private int groupLevel;
    @JsonProperty(value = "teacher")
    private String teacher;
    @JsonProperty(value = "dance")
    private String dance;
    @JsonProperty(value = "status")
    private String status;

    public SheduleReport() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setGroupLevel(int groupLevel) {
        this.groupLevel = groupLevel;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setDance(String dance) {
        this.dance = dance;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
