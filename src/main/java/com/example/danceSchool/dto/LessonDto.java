package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public class LessonDto extends BaseDto {
    @JsonProperty(value = "begin")
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm", timezone = "Europe/Moscow")
    private Date begin;
    @JsonProperty(value = "end")
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm", timezone = "Europe/Moscow")
    private Date end;

    private int length;

    @JsonProperty(value = "group")
    private GroupDto group;
    @JsonProperty(value = "teacher")
    private TeacherDto teacher;
    @JsonProperty(value = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end, length, group, teacher, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LessonDto lessonDto = (LessonDto) o;
        return length == lessonDto.length &&
                Objects.equals(begin, lessonDto.begin) &&
                Objects.equals(end, lessonDto.end) &&
                Objects.equals(group, lessonDto.group) &&
                Objects.equals(teacher, lessonDto.teacher) &&
                Objects.equals(status, lessonDto.status);
    }
}
