package com.example.danceSchool.controller;

import com.example.danceSchool.dto.LessonDto;
import com.example.danceSchool.dto.PersonDto;
import com.example.danceSchool.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/(id)")
    public PersonDto getPersonById (@PathParam("id") Long id, @RequestHeader HttpHeaders httpHeaders){
        return personService.getPersonById(id);
    }
    @GetMapping
    public List<PersonDto> getAll(){
        return personService.getAll();
    }
    @GetMapping("/map")
    public Map<Long, PersonDto> getMap(){
        return personService.getAll().stream().collect(Collectors.toMap(PersonDto::getId, Function.identity()));
    }
    @PostMapping()
    public PersonDto createPerson (@RequestBody PersonDto personDto){
        return personService.createPerson(personDto);
    }
    @PutMapping()
    public PersonDto updatePerson (PersonDto personDto){
        return personService.updatePerson(personDto);
    }
    @DeleteMapping
    public void deletePerson (Long id) {
        personService.deletePerson(id);
    }
}
