package com.example.danceSchool.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends EntityBase implements GrantedAuthority {
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Person> persons;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}