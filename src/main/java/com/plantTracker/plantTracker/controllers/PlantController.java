package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.PlantTrackerApplication;
import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.models.PlantDTO;
import com.plantTracker.plantTracker.models.enums.Priority;
import com.plantTracker.plantTracker.services.CountryService;
import com.plantTracker.plantTracker.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value= "/plants")
public class PlantController {

    @Autowired
    PlantService plantService;

    @Autowired
    CountryService countryService;


    // CREATE: POST - localhost:8080/plants
    @PostMapping
    public ResponseEntity<List<Plant>> postPlant(@RequestBody PlantDTO plantDTO){
       plantService.addNewPlant(plantDTO);
       List<Plant> plants = plantService.getAllPlants();
       return new ResponseEntity<>(plants, HttpStatus.OK);
    }

    // INDEX: GET -  localhost:8080/plants
    @GetMapping
    public ResponseEntity<List<Plant>>getAllPlants(){
        List<Plant> plants = plantService.getAllPlants();
        return new ResponseEntity<>(plants, HttpStatus.OK);
    }

    // SHOW: GET -  localhost:8080/plants/id
    @GetMapping(value ="/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id){
        Optional<Plant> plantOptional = plantService.getPlantById(id);
        if(plantOptional.isPresent()){
            return new ResponseEntity<>(plantOptional.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // PARTIAL UPDATE: PATCH -  localhost:8080/plants/id
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Plant> updatePlantPartial(@PathVariable Long id, @RequestBody Map<String, String> updatePayload){

        Plant plant = plantService.updatePlantPartial(id, updatePayload);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    // WATER PLANT (PARTIAL UPDATE): PATCH -  localhost:8080/plants/id/water-plant
    @PatchMapping(value = "/{id}/water-plant")
    public ResponseEntity<Plant> waterPlantUpdate(@PathVariable long id){
        Plant plant = plantService.waterPlant(id);
        if (!Objects.isNull(plant)){
            return new ResponseEntity<>(plant, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    // FULL UPDATE: PUT - localhost:8080/plants/id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Plant> updatePlantFull(@PathVariable Long id, @RequestBody PlantDTO plantDTO){
        Plant plant = plantService.updatePlantFull(id, plantDTO);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    // DELETE: DELETE -  localhost:8080/plants/id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id){
        plantService.deletePlant(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // INDEX: GET -  localhost:8080/plants/message/id
    @GetMapping(value = "/message/{id}")
    public ResponseEntity<String> provideInstruction(@PathVariable Long id){
        String message = plantService.provideInstruction(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}









