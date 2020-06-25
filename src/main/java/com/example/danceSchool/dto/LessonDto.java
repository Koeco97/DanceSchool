package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class LessonDto extends BaseDto {
    @JsonProperty(value = "date")
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm")
    private Date date;
    @JsonProperty(value = "group")
    private GroupDto group;
    @JsonProperty(value = "comment")
    private String comment;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }
}
