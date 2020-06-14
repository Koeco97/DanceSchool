package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class LessonDto implements Serializable {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "date")
    private Date date;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
