package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.Duty;
import com.plantTracker.plantTracker.models.DutyDTO;
import com.plantTracker.plantTracker.services.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/duties")
public class DutyController {

    @Autowired
    DutyService dutyService;

    @GetMapping
    public ResponseEntity<List<Duty>> getAllDuties(){
        List<Duty> duties = dutyService.getAllDuties();
        return new ResponseEntity<>(duties, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Duty> getDutyById(@PathVariable Long id){
        Optional<Duty> dutyOptional = dutyService.getDutyById(id);
        if (dutyOptional.isPresent()){
            return new ResponseEntity<>(dutyOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Duty> addNewDuty(@RequestBody DutyDTO dutyDTO){
        Duty duty = dutyService.addNewDuty(dutyDTO);
        if (Objects.isNull(duty)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(duty, HttpStatus.CREATED);
        }

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Duty> updateDutyPlant(@PathVariable Long id, @RequestBody Map<String, Long> plantOrPersonPayload){
        Optional<Duty> dutyOptional = dutyService.getDutyById(id);
        if(dutyOptional.isPresent()){
            if (!Objects.isNull(plantOrPersonPayload.get("plantId"))){
                Long plantId = plantOrPersonPayload.get("plantId");
                Duty duty = dutyService.updateDutyPlant(id, plantId);
                return new ResponseEntity<>(duty, HttpStatus.OK);

            } else if (!Objects.isNull(plantOrPersonPayload.get("personId"))) {
                Long personId = plantOrPersonPayload.get("personId");
                Duty duty = dutyService.updateDutyPerson(id, personId);
                return new ResponseEntity<>(duty, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Duty> updateDutyFull(@PathVariable Long id, @RequestBody DutyDTO dutyDTO ){
        Optional<Duty> dutyOptional = dutyService.getDutyById(id);
        if(dutyOptional.isPresent()){
            Long plantId = dutyDTO.getPlantId();
            Long personId = dutyDTO.getPersonId();
            Duty duty = dutyService.updateDutyFull(id, plantId, personId);
            return new ResponseEntity<>(duty, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



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
