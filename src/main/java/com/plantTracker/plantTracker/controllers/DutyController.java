package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.models.Duty;
import com.plantTracker.plantTracker.models.DutyDTO;
import com.plantTracker.plantTracker.services.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    @PostMapping
    public ResponseEntity<Duty> addNewDuty(@RequestBody DutyDTO dutyDTO){
        Duty duty = dutyService.addNewDuty(dutyDTO);
        return new ResponseEntity<>(duty, HttpStatus.CREATED);
    }


//    PARTIAL UPDATE: PATCH - localhost:8080/duties/id
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Duty> updateDutyPlant(@RequestParam Long id, Map<String, Long> plantPayload){
        Long plantId = plantPayload.get("plantId");
        Duty duty = dutyService.updateDutyPlant(id, plantId);
        return new ResponseEntity<>(duty, HttpStatus.OK);
    }

// PARTIAL UPDATE
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Duty> updateDutyPerson(@RequestParam Long id, Map<String, Long> personPayload){
        Long personId = personPayload.get("personId");
        Duty duty = dutyService.updateDutyPerson(id, personId);
        return new ResponseEntity<>(duty, HttpStatus.OK);

    }


//    FULL UPDATE: PUT - localhost:8080/duties/id

//    DELETE: DELETE - localhost:8080/duties/id

}
