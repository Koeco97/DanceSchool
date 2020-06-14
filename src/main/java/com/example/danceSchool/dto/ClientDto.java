package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;

public class ClientDto extends PersonDto{
    @JsonProperty(value = "group")
    private GroupDto group;

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }
}
