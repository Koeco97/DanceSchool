package com.example.danceSchool.service;

import com.example.danceSchool.dto.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto getPersonById (Long id);
    PersonDto createPerson(PersonDto personDto);

    PersonDto updatePerson(PersonDto personDto, Long id);

    void deletePerson(Long id);
    public List<PersonDto> getAll();
}
