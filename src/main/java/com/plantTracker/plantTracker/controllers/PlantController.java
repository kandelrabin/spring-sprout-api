package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.models.*;
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
        Optional<Plant> plantOptional = plantService.getPlantById(id);
        if(plantOptional.isPresent()){
            Plant plant = plantService.updatePlantPartial(id, updatePayload);
            return new ResponseEntity<>(plant, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping(value = "/{id}/water-plant")
    public ResponseEntity<Plant> waterPlantUpdate(@PathVariable long id){
        Plant plant = plantService.waterPlant(id);
        if (!Objects.isNull(plant)){
            return new ResponseEntity<>(plant, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Plant> updatePlantFull(@PathVariable Long id, @RequestBody PlantDTO plantDTO){
        Optional<Plant> plantOptional = plantService.getPlantById(id);
        if(plantOptional.isPresent()){
            Plant plant = plantService.updatePlantFull(id, plantDTO);
            return new ResponseEntity<>(plant, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id){
        Optional<Plant> plantOptional = plantService.getPlantById(id);
        if(plantOptional.isPresent()){
            plantService.deletePlant(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/message/{id}")
    public ResponseEntity<InstructionDTO> provideInstruction(@PathVariable long id){
        Optional<Plant> plantOptional = plantService.getPlantById(id);
        if(plantOptional.isPresent()){
            String message = plantService.provideInstruction(id);
            InstructionDTO instructionDTO = new InstructionDTO(id,message);
            return new ResponseEntity<>(instructionDTO,HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/plant-info/{id}")
    public ResponseEntity<PlantInformationDTO> plantInfo(@PathVariable long id){
        Optional<Plant> plantOptional = plantService.getPlantById(id);
        if (plantOptional.isPresent()){

            String infoMessage = plantService.plantInformation(id);
            PlantInformationDTO plantInformationDTO = new PlantInformationDTO(id,infoMessage);
            return new ResponseEntity<>(plantInformationDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/countdown/{id}")
    public ResponseEntity<CountdownDTO> getCountdownDays(@PathVariable long id) throws Exception {
        Optional<Plant> plantOptional = plantService.getPlantById(id);
        if(plantOptional.isPresent()){
            String countdown = plantService.getCountdownTime(id);
            CountdownDTO countdownDTO = new CountdownDTO(id,countdown);
            return new ResponseEntity<>(countdownDTO, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}









