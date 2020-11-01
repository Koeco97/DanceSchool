package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class GroupDto extends BaseDto {
    @JsonProperty(value = "group_level")
    private String groupLevel;
    @JsonProperty(value = "dance")
    private DanceDto dance;

    public String getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(String groupLevel) {
        this.groupLevel = groupLevel;
    }

    public DanceDto getDance() {
        return dance;
    }

    public void setDance(DanceDto dance) {
        this.dance = dance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupLevel, dance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupDto groupDto = (GroupDto) o;
        return groupLevel == groupDto.groupLevel &&
                Objects.equals(dance, groupDto.dance);
    }
}
