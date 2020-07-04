package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DanceDto extends BaseDto {
    @JsonProperty(value = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DanceDto danceDto = (DanceDto) o;
        return Objects.equals(name, danceDto.name);
    }
}

