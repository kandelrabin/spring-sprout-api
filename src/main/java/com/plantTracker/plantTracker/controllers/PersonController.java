package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.models.Person;
import com.plantTracker.plantTracker.services.PersonService;
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


    @PostMapping
    public ResponseEntity<Person> addNewPerson(@RequestBody Map<String, String> personPayload){
        if (!personPayload.get("name").isBlank()){
            String name = personPayload.get("name");
            Person person = personService.addNewPerson(name);
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        Optional<Person> personOptional = personService.getPersonById(id);
        if (personOptional.isPresent()){
            return new ResponseEntity<>(personOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople(){
        List<Person> people = personService.getAllPeople();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Map<String, String> updatePayLoad){
        if (!updatePayLoad.get("name").isBlank()){
            String name = updatePayLoad.get("name");
            Person person = personService.updatePerson(id, name);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id){
        Optional<Person> personOptional = personService.getPersonById(id);
        if (personOptional.isPresent()){
            personService.deletePerson(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }













}
