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
    public ResponseEntity<Duty> getDutyById(@PathVariable Long id){
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
    public ResponseEntity<Duty> updateDutyPlant(@PathVariable Long id, Map<Optional<String>, Optional<Long>> plantOrPersonPayload){

        if (plantOrPersonPayload.get("plantId").isPresent()){
            Long plantId = plantOrPersonPayload.get("plantId").get();
            Duty duty = dutyService.updateDutyPlant(id, plantId);
            return new ResponseEntity<>(duty, HttpStatus.OK);

        } else if (plantOrPersonPayload.get("personId").isPresent()) {
            Long personId = plantOrPersonPayload.get("personId").get();
            Duty duty = dutyService.updateDutyPerson(id, personId);
            return new ResponseEntity<>(duty, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }


    }


//    FULL UPDATE: PUT - localhost:8080/duties/id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Duty> updateDutyFull(@PathVariable Long id, Map<String, Long> plantPersonPayload){
        Long plantId = plantPersonPayload.get("plantId");
        Long personId = plantPersonPayload.get("personId");
        Duty duty = dutyService.updateDutyFull(id, plantId, personId);
        return new ResponseEntity<>(duty, HttpStatus.OK);
    }

//    DELETE: DELETE - localhost:8080/duties/id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDuty(@PathVariable Long id){
        Optional<Duty> dutyOptional = dutyService.getDutyById(id);
        if (dutyOptional.isPresent()){
            dutyService.deleteDuty(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
