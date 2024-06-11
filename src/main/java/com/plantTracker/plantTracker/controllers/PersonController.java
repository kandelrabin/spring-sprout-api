package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.models.Person;
import com.plantTracker.plantTracker.services.PersonService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/people")
public class PersonController {

    @Autowired
    PersonService personService;

    //    CREATE: POST - localhost:8080/people
    @PostMapping
    public ResponseEntity<Person> addNewPerson(Map<Optional<String>, Optional<String>> personPayload){
        if (personPayload.get("name").isPresent()){
            Person person = personService.addNewPerson(personPayload.get("name").get());
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //    SHOW: GET - localhost:8080/people/id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        Optional<Person> personOptional = personService.getPersonById(id);
        if (personOptional.isPresent()){
            return new ResponseEntity<>(personOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //    INDEX: GET - localhost:8080/people
    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople(){
        List<Person> people = personService.getAllPeople();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }


}
