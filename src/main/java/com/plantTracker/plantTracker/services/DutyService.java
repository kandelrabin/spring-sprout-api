package com.plantTracker.plantTracker.services;

import com.plantTracker.plantTracker.models.Duty;
import com.plantTracker.plantTracker.models.DutyDTO;
import com.plantTracker.plantTracker.models.Person;
import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.repositories.DutyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DutyService {

    @Autowired
    DutyRepository dutyRepository;

    @Autowired
    PlantService plantService;

    @Autowired
    PersonService personService;

    public Duty addNewDuty(DutyDTO dutyDTO){
        long plantId = dutyDTO.getPlantId();
        long personId = dutyDTO.getPersonId();

        Plant plant = plantService.getPlantById(plantId).get();
        Person person = personService.getPersonById(personId).get();
        Duty duty = new Duty(plant, person);

        dutyRepository.save(duty);
        return duty;
    }

}
