package com.plantTracker.plantTracker.services;

import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.models.PlantDTO;
import com.plantTracker.plantTracker.models.enums.Priority;
import com.plantTracker.plantTracker.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlantService {

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    CountryService countryService;

    public Plant addNewPlant(PlantDTO plantDTO){

        String name = plantDTO.getName();
        Priority priority = Priority.valueOf(plantDTO.getPriority());
        String lastWatered = plantDTO.getLastWatered();
        long countryId = plantDTO.getCountryId();
        Country country = countryService.getCountryById(countryId).get();
        Plant plant = new Plant(name, priority, lastWatered, country);

        plantRepository.save(plant);

        return plant;
    }

    public Optional<Plant> getPlantById(long id){
        return plantRepository.findById(id);
    }

}
