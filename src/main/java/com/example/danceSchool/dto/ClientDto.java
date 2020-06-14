package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;

public class ClientDto extends PersonDto implements Serializable {
    @JsonProperty(value = "id")
    private Long id;
    private GroupDto group;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }
}
