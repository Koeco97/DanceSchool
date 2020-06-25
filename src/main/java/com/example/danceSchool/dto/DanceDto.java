package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DanceDto extends BaseDto {
    @JsonProperty(value = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

