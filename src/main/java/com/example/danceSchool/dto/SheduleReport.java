package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SheduleReport {

    @JsonProperty(value = "id")
    Long id;
    @JsonProperty(value = "begin")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Moscow")
    private Date begin;
    @JsonProperty(value = "end")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Moscow")
    private Date end;
    @JsonProperty(value = "length")
    private int length;
    @JsonProperty(value = "group_level")
    private String groupLevel;
    @JsonProperty(value = "teacher_id")
    private Long teacherId;
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

    public Long getId() {
        return id;
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

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    public int getLength() {
        return length;
    }

    public String getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(String groupLevel) {
        this.groupLevel = groupLevel;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getDance() {
        return dance;
    }

    public String getStatus() {
        return status;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
