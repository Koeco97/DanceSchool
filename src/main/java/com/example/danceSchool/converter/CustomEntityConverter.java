package com.example.danceSchool.converter;

import com.example.danceSchool.entity.Person;
import com.example.danceSchool.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomEntityConverter<T extends Person> {
    private final RoleRepository roleRepository;

    @Autowired
    CustomEntityConverter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public T convert(T person, Class<T> newPerson) {
        T target = null;
        try {
            target = newPerson.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            target = newPerson.newInstance();
            target.setId(person.getId());
            target.setFirstName(person.getFirstName());
            target.setSecondName(person.getSecondName());
            target.setLastName(person.getLastName());
            target.setBirthday(person.getBirthday());
            target.setSex(person.getSex());
            target.setEmail(person.getEmail());
            target.setPhoneNumber(person.getPhoneNumber());
            target.setLogin(person.getLogin());
            target.setPassword(person.getPassword());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return target;
    }
}
