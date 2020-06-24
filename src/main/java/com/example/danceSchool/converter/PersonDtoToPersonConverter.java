package com.example.danceSchool.converter;

import com.example.danceSchool.dto.PersonDto;
import com.example.danceSchool.entity.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoToPersonConverter implements Converter<PersonDto, Person> {
    public static void copyProperties(PersonDto personDto, Person target) {
        target.setFirstName(personDto.getFirstName());
        target.setSecondName(personDto.getSecondName());
        target.setLastName(personDto.getLastName());
        target.setBirthday(personDto.getBirthday());
        target.setSex(personDto.getSex());
        target.setEmail(personDto.getEmail());
        target.setPhoneNumber(personDto.getPhoneNumber());
        target.setLogin(personDto.getLogin());
        target.setPassword(personDto.getPassword());
    }

    @Override
    public Person convert(PersonDto personDto) {
        Person target = new Person();
        copyProperties(personDto, target);
        return target;
    }
}
