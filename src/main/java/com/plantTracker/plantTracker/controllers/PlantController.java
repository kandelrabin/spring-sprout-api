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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value= "/plants")
public class PlantController {

    @Autowired
    PlantService plantService;

    @Autowired
    CountryService countryService;

    @PostMapping
    public ResponseEntity<List<Plant>> postPlant(PlantDTO plantDTO){
       plantService.addNewPlant(plantDTO);
       List<Plant> plants = plantService.getAllPlants();
       return new ResponseEntity<>(plants, HttpStatus.OK);

    }

}









