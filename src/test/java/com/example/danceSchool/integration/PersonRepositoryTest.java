package com.example.danceSchool.integration;

import com.example.danceSchool.DanceSchoolApplication;
import com.example.danceSchool.entity.Person;
import com.example.danceSchool.repository.PersonRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DanceSchoolApplication.class)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;
    private Person personForEach;

    @Before
    public void before() {
        personForEach = new Person();
        personForEach.setEmail("name@mail.ru");
        personRepository.save(personForEach);
    }

    @After
    public void after() {
        personRepository.deleteAll();
    }

    @Test
    public void findByEmailTest() {
        Person result = personRepository.findByEmail("name@mail.ru");
        Assert.assertEquals(personForEach, result);
    }
}
