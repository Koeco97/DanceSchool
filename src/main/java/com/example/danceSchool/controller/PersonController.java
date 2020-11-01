package com.example.danceSchool.controller;

import com.example.danceSchool.dto.PersonDto;
import com.example.danceSchool.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/{id}")
    public PersonDto getPersonById(@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders) {
        return personService.getPersonById(id);
    }

    @GetMapping
    public Map<String, List<PersonDto>> getAll() {
        Map<String, List<PersonDto>> personMap = new HashMap<String, List<PersonDto>>();
        personMap.put("persons", personService.getAll());
        return personMap;
    }

    @GetMapping("/map")
    public Map<Long, PersonDto> getMap() {
        return personService.getAll().stream().collect(Collectors.toMap(PersonDto::getId, Function.identity()));
    }

    @PostMapping()
    public PersonDto createPerson(@RequestBody PersonDto personDto) {
        return personService.createPerson(personDto);
    }

    @PutMapping(value = "/{email}")
    public PersonDto updatePerson(@PathVariable("email") String email, @RequestBody PersonDto personDto) {
        return personService.updatePerson(personDto, email);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }
}
