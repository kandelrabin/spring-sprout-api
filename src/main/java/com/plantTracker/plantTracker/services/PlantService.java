package com.plantTracker.plantTracker.services;

import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.Duty;
import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.models.PlantDTO;
import com.plantTracker.plantTracker.models.enums.Priority;
import com.plantTracker.plantTracker.repositories.DutyRepository;
import com.plantTracker.plantTracker.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlantService {

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    DutyRepository dutyRepository;

    //    CREATE: POST - localhost:8080/people
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

    //    SHOW: GET - localhost:8080/people/id
    public Optional<Plant> getPlantById(long id){
        return plantRepository.findById(id);
    }

    //    INDEX: GET - localhost:8080/people
    public List<Plant> getAllPlants(){
        return plantRepository.findAll();
    }

//    PARTIAL UPDATE: PATCH - localhost:8080/people/id
// to update one variable at once
    public Plant updatePlantPartial(long id, Map< Optional<String>, Optional<String>> updatePayload){
        Plant plant = plantRepository.findById(id).get();

        if (updatePayload.get("name").isPresent()){
            plant.setName(updatePayload.get("name").get());

        } else if (updatePayload.get("priotity").isPresent()) {
            Priority priority = Priority.valueOf(updatePayload.get("priority").get());
            plant.setPriority(priority);
        } else if (updatePayload.get("lastWatered").isPresent()) {
            plant.setLastWatered(updatePayload.get("lastWatered").get());

        }else if (updatePayload.get("countryId").isPresent()){
            Long countryId = Long.parseLong(updatePayload.get("countryId").get());
            Country country = countryService.getCountryById(countryId).get();

            plant.setCountry( country);
        } else{
            return null;
        }
         plantRepository.save(plant);

        return plant;
    }

//    FULL UPDATE: PUT - localhost:8080/people/id
    public Plant updatePlantFull(Long id, PlantDTO plantDTO){
       Plant plant = plantRepository.findById(id).get();

       String name = plantDTO.getName();
       plant.setName(name);

       String priorityStr = plantDTO.getPriority();
       Priority priority = Priority.valueOf(priorityStr);
       plant.setPriority(priority);

       String lastWatered = plantDTO.getLastWatered();
       plant.setLastWatered(lastWatered);

       Long countryId = plantDTO.getCountryId();
       Country country = countryService.getCountryById(countryId).get();
       plant.setCountry(country);

       plantRepository.save(plant);

       return plant;

    }

//    DELETE: DELETE - localhost:8080/people/id
    public void deletePlant(long id){
        Plant plant = getPlantById(id).get();
        for (Duty duty : plant.getDuties()){
            dutyRepository.deleteById(duty.getId());
        }
        plantRepository.deleteById(id);

    }



  }



