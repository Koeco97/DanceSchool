package com.example.danceSchool.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "lesson")
public class Lesson extends EntityBase {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @Column(name = "date")
    private Date date;
    @Column(name = "comment")
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
