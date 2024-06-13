package com.plantTracker.plantTracker.services;

import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.Duty;
import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.models.PlantDTO;
import com.plantTracker.plantTracker.models.enums.Priority;
import com.plantTracker.plantTracker.models.plantChild.Climbers;
import com.plantTracker.plantTracker.models.plantChild.Succulents;
import com.plantTracker.plantTracker.repositories.DutyRepository;
import com.plantTracker.plantTracker.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlantService {

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    DutyRepository dutyRepository;


    //SPECIAL CARE METHOD
    public String provideInstruction(long id){
        Plant plant = plantRepository.findById(id).get();
        return plant.provideInstruction();
    }

    // WATER PLANT METHOD (PARTIAL UPDATE): PATCH -  localhost:8080/plants/id/water-plant
    public Plant waterPlant(long id){
        Plant plant = plantRepository.findById(id).get();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formatter.format(date);

        if (plant.getLastWateredDates().contains(currentDate)){
            return null;
        } else {
            plant.addToLastWateredDates(currentDate);
            plantRepository.save(plant);
        }
        return plant;
    }

    // PROVIDE PLANT INFORMATION
    public String plantInformation(long id){
        Plant plant = plantRepository.findById(id).get();

        String name = plant.getName();
        int age = plant.getAge();
        String lastWateredDate;
        if (!plant.getLastWateredDates().isEmpty()){
            lastWateredDate = plant.getLastWateredDates().get(plant.getLastWateredDates().size()-1);
        } else {
            lastWateredDate = "";
        }
        String country = String.valueOf(plant.getCountry().getName());

        String dateMessage = (lastWateredDate.isEmpty()) ? "It has never been watered." : String.format("It was last watered on %s.", lastWateredDate);

        String infoMessage = String.format("This %s is %d year(s) old and it originates from %s. %s See care instructions for how to look after this!", name, age, country, dateMessage);

        return infoMessage;
    }

    // CREATE: POST - localhost:8080/plants
    public Plant addNewPlant(PlantDTO plantDTO){
        String name = plantDTO.getName();
        int age = plantDTO.getAge();
        Priority priority = Priority.valueOf(plantDTO.getPriority());
        long countryId = plantDTO.getCountryId();
        Country country = countryService.getCountryById(countryId).get();
        Plant plant;
        if (plantDTO.getPlantType().equalsIgnoreCase("climbers")){
            plant = new Climbers(name, age, priority, country);
        }else if (plantDTO.getPlantType().equalsIgnoreCase("succulents")){
            plant = new Succulents(name, age, priority, country);
        }else{
            plant = new Plant(name, age, priority, country);
        }
        plantRepository.save(plant);
        return plant;
    }

    //    SHOW: GET - localhost:8080/plants/id
    public Optional<Plant> getPlantById(long id){
        return plantRepository.findById(id);
    }

    //    INDEX: GET - localhost:8080/plants
    public List<Plant> getAllPlants(){
        return plantRepository.findAll();
    }

//    PARTIAL UPDATE: PATCH - localhost:8080/plants/id
// to update one variable at once
    public Plant updatePlantPartial(long id, Map< String, String> updatePayload){
        Plant plant = plantRepository.findById(id).get();

        if (!Objects.isNull(updatePayload.get("name"))){
            plant.setName(updatePayload.get("name"));

        } else if (!Objects.isNull(updatePayload.get("age"))) {
            plant.setAge(Integer.parseInt(updatePayload.get("age")));

        } else if (!Objects.isNull(updatePayload.get("priority"))) {
            Priority priority = Priority.valueOf(updatePayload.get("priority"));
            plant.setPriority(priority);

        } else if (!Objects.isNull(updatePayload.get("countryId"))){
            Long countryId = Long.parseLong(updatePayload.get("countryId"));
            Country country = countryService.getCountryById(countryId).get();

            plant.setCountry( country);
        } else{
            return null;
        }
        plantRepository.save(plant);
        return plant;
    }

//    FULL UPDATE: PUT - localhost:8080/plants/id
    public Plant updatePlantFull(Long id, PlantDTO plantDTO){
       Plant plant = plantRepository.findById(id).get();

       String name = plantDTO.getName();
       plant.setName(name);

       int age = plantDTO.getAge();
       plant.setAge(age);

       String priorityStr = plantDTO.getPriority();
       Priority priority = Priority.valueOf(priorityStr);
       plant.setPriority(priority);

       Long countryId = plantDTO.getCountryId();
       Country country = countryService.getCountryById(countryId).get();
       plant.setCountry(country);

       plantRepository.save(plant);

       return plant;

    }

//    DELETE: DELETE - localhost:8080/plants/id
    public void deletePlant(long id){
        Plant plant = getPlantById(id).get();
        for (Duty duty : plant.getDuties()){
            dutyRepository.deleteById(duty.getId());
        }
        plantRepository.deleteById(id);

    }


  }



