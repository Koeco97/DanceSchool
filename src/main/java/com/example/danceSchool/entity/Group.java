package com.example.danceSchool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "group")
public class Group extends EntityBase{
    @Column(name = "group_level")
    private int groupLevel;
    @Column(name = "teacher_id")
    private Long teacherId;
    @Column(name = "dance_id")
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
}
