package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.models.PlantDTO;
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

    @PostMapping
    public ResponseEntity<List<Plant>> postPlant(@RequestBody PlantDTO plantDTO){
       plantService.addNewPlant(plantDTO);
       List<Plant> plants = plantService.getAllPlants();
       return new ResponseEntity<>(plants, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Plant>>getAllPlants(){
        List<Plant> plants = plantService.getAllPlants();
        return new ResponseEntity<>(plants, HttpStatus.OK);
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id){
        Optional<Plant> plantOptional = plantService.getPlantById(id);
        if(plantOptional.isPresent()){
            return new ResponseEntity<>(plantOptional.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Plant> updatePlantPartial(@PathVariable Long id, @RequestBody Map<String, String> updatePayload){

        Plant plant = plantService.updatePlantPartial(id, updatePayload);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/water-plant")
    public ResponseEntity<Plant> waterPlantUpdate(@PathVariable long id){
        Plant plant = plantService.waterPlant(id);
        if (!Objects.isNull(plant)){
            return new ResponseEntity<>(plant, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Plant> updatePlantFull(@PathVariable Long id, @RequestBody PlantDTO plantDTO){
        Plant plant = plantService.updatePlantFull(id, plantDTO);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id){
        plantService.deletePlant(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/message/{id}")
    public ResponseEntity<String> provideInstruction(@PathVariable long id){
        String message = plantService.provideInstruction(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping(value = "/plant-info/{id}")
    public ResponseEntity<String> plantInfo(@PathVariable long id){
        Optional<Plant> plantOptional = plantService.getPlantById(id);
        if (plantOptional.isPresent()){
            String infoMessage = plantService.plantInformation(id);
            return new ResponseEntity<>(infoMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/countdown/{id}")
    public ResponseEntity<String> getCountdownDays(@PathVariable long id) throws Exception {
        Optional<Plant> plantOptional = plantService.getPlantById(id);
        if(plantOptional.isPresent()){
            String countdown = plantService.getCountdownTime(id);
            return new ResponseEntity<>(countdown, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

}









