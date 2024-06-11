package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.models.Duty;
import com.plantTracker.plantTracker.services.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/duties")
public class DutyController {

    @Autowired
    DutyService dutyService;

//    INDEX: GET - localhost:8080/duties
    @GetMapping
    public ResponseEntity<List<Duty>> getAllDuties(){
        List<Duty> duties = dutyService.getAllDuties();
        return new ResponseEntity<>(duties, HttpStatus.OK);
    }

//    SHOW: GET - localhost:8080/duties/id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Duty> getDutyById(@RequestParam Long id){
        Optional<Duty> dutyOptional = dutyService.getDutyById(id);
        if (dutyOptional.isPresent()){
            return new ResponseEntity<>(dutyOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

//    CREATE: POST - localhost:8080/duties


//    PARTIAL UPDATE: PATCH - localhost:8080/duties/id

//    FULL UPDATE: PUT - localhost:8080/duties/id

//    DELETE: DELETE - localhost:8080/duties/id

}
