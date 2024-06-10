package com.plantTracker.plantTracker.services;

import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantService {

    @Autowired
    PlantRepository plantRepository;

    public Plant addNewPlant(Plant plant){
        plantRepository.save(plant);

        return plant;
    }

}
