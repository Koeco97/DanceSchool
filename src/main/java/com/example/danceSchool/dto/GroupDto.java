package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;

public class GroupDto implements Serializable {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "group_level")
    private Integer groupLevel;
    @JsonProperty(value = "teacher_id")
    private Long teacherId;
    @JsonProperty(value = "dance_id")
    private Long danceId;

    public int getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(int groupLevel) {
        this.groupLevel = groupLevel;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getDanceId() {
        return danceId;
    }

    public void setDanceId(Long danceId) {
        this.danceId = danceId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
