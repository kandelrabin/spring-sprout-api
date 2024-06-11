package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.models.Person;
import com.plantTracker.plantTracker.services.PersonService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/people")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<Person> addNewPerson(Map<Optional<String>, Optional<String>> personPayload){
        if (personPayload.get("name").isPresent()){
            Person person = personService.addNewPerson(personPayload.get("name").get());
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }


}
