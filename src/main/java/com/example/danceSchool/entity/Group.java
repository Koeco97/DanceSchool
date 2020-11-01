package com.example.danceSchool.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"group\"")
public class Group extends EntityBase {
    @Column(name = "group_level")
    private String groupLevel;
    @ManyToOne
    @JoinColumn(name = "dance_id")
    private Dance dance;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Client> clients;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    public String getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(String groupLevel) {
        this.groupLevel = groupLevel;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Dance getDance() {
        return dance;
    }

    public void setDance(Dance dance) {
        this.dance = dance;
    }

    public void addClient(Client client) {
        clients.add(client);
    }
    
}
