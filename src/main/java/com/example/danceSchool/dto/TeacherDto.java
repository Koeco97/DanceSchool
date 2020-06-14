package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;

public class TeacherDto extends PersonDto implements Serializable {
    @JsonProperty(value = "id")
    private Long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
