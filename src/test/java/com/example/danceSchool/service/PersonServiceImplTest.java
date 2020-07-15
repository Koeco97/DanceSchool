package com.example.danceSchool.service;

import com.example.danceSchool.dto.PersonDto;
import com.example.danceSchool.entity.Person;
import com.example.danceSchool.exception.PersonException;
import com.example.danceSchool.repository.PersonRepository;
import com.example.danceSchool.service.impl.PersonServiceImpl;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {
    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private ConversionService conversionService;

    @org.junit.Test
    public void verifyGetPersonByIdFromRepository() {
        Mockito.when(personRepository.findById(any())).thenReturn(Optional.of(new Person()));
        personService.getPersonById(1L);
        Mockito.verify(personRepository).findById(any());
    }

    @org.junit.Test
    public void testGetPersonByIdPersonNotFound() {
        Mockito.when(personRepository.findById(any())).thenReturn(Optional.empty());
        try {
            personService.getPersonById(1L);
            fail();
        } catch (PersonException e) {
            Assert.assertEquals("Person is not found", e.getMessage());
        }
    }

    @org.junit.Test
    public void checkConvertPerson() {
        Person exceptedPerson = new Person();
        PersonDto exceptedPersonDto = new PersonDto();
        Mockito.when(personRepository.findById(any())).thenReturn(Optional.of(exceptedPerson));
        Mockito.when(conversionService.convert(exceptedPerson, PersonDto.class)).thenReturn(exceptedPersonDto);
        PersonDto result = personService.getPersonById(1L);
        Mockito.verify(conversionService).convert(exceptedPerson, PersonDto.class);
        Assert.assertSame(exceptedPersonDto, result);
    }

    @org.junit.Test
    public void getAllPersonsTest() {
        Person person1 = new Person();
        Person person2 = new Person();
        Mockito.when(personRepository.findAll()).thenReturn(Stream.of(person1, person2).collect(Collectors.toList()));
        assertEquals(2, personService.getAll().size());
    }

    @org.junit.Test
    public void deletePersonTest() {
        Person person = new Person();
        Mockito.when(personRepository.findById(any())).thenReturn(Optional.of(new Person()));
        personService.deletePerson(1L);
        Mockito.verify(personRepository, Mockito.times(1)).delete(person);
    }
}