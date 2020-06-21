package com.example.danceSchool.service.impl;

import com.example.danceSchool.dto.PersonDto;
import com.example.danceSchool.entity.Person;
import com.example.danceSchool.exception.PersonException;
import com.example.danceSchool.repository.PersonRepository;
import com.example.danceSchool.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ConversionService conversionService;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, ConversionService conversionService) {
        this.personRepository = personRepository;
        this.conversionService = conversionService;
    }

    @Override
    public PersonDto getPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(()->new PersonException("Person is not found"));
        return conversionService.convert(person, PersonDto.class);
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = conversionService.convert(personDto, Person.class);
        return conversionService.convert(personRepository.save(person), PersonDto.class);
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) {
        Person person = personRepository.findById(personDto.getId()).orElseThrow(()->new PersonException("Person is not found"));
        person.setSecondName(personDto.getSecondName());
        person.setLastName(personDto.getLastName());
        person.setBirthday(personDto.getBirthday());
        person.setSex(personDto.getSex());
        person.setEmail(personDto.getEmail());
        person.setPhoneNumber(personDto.getPhoneNumber());
        person.setLogin(personDto.getLogin());
        person.setPassword(personDto.getPassword());
        return conversionService.convert(personRepository.save(person), PersonDto.class);
    }

    @Override
    public void deletePerson(Long id) {
        Person person = personRepository.findById(id).orElseThrow(()->new PersonException("Person is not found"));
        personRepository.delete(person);
    }

    @Override
    public List<PersonDto> getAll() {
        List <Person> persons = personRepository.findAll();
        return persons.stream().map(person->conversionService.convert(person, PersonDto.class)).collect(Collectors.toList());
    }
}
