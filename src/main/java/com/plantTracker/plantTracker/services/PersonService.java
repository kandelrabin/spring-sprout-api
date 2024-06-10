package com.plantTracker.plantTracker.services;

import com.plantTracker.plantTracker.models.Person;
import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person addNewPerson(String name){
        Person person = new Person(name);
        personRepository.save(person);
        return person;
    }

    public Optional<Person> getPersonById(long id){
        return personRepository.findById(id);
    }

}
