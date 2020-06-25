package com.example.danceSchool.converter;

import com.example.danceSchool.dto.PersonDto;
import com.example.danceSchool.entity.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonDtoConverter implements Converter<Person, PersonDto> {
    public static void copyProperties(Person person, PersonDto target) {
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
    }

    @Override
    public PersonDto convert(Person person) {
        PersonDto target = new PersonDto();
        copyProperties(person, target);
        return target;
    }
}
