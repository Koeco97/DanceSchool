package com.example.danceSchool.controller;

import com.example.danceSchool.dto.PersonDto;
import com.example.danceSchool.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PersonDto getPersonById (@PathVariable("id") Long id, @RequestHeader HttpHeaders httpHeaders){
        return personService.getPersonById(id);
    }
    @GetMapping
    public List<PersonDto> getAll(){
        return personService.getAll();
    }

    @GetMapping("/map")
    public Map<Long, PersonDto> getMap() {
        return personService.getAll().stream().collect(Collectors.toMap(PersonDto::getId, Function.identity()));
    }

    @PostMapping()
    public PersonDto createPerson(@RequestBody PersonDto personDto) {
        return personService.createPerson(personDto);
    }

    @PutMapping(value = "/{id}")
    public PersonDto updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto) {
        return personService.updatePerson(personDto, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }
}
