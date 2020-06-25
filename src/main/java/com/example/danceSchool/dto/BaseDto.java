package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BaseDto implements Serializable {
    @JsonProperty(value = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
