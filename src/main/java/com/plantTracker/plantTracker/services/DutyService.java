package com.plantTracker.plantTracker.services;

import com.plantTracker.plantTracker.models.Duty;
import com.plantTracker.plantTracker.models.DutyDTO;
import com.plantTracker.plantTracker.models.Person;
import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.repositories.DutyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyService {

    @Autowired
    DutyRepository dutyRepository;

    @Autowired
    PlantService plantService;

    @Autowired
    PersonService personService;

    // CREATE
    public Duty addNewDuty(DutyDTO dutyDTO){
        long plantId = dutyDTO.getPlantId();
        long personId = dutyDTO.getPersonId();

        Plant plant = plantService.getPlantById(plantId).get();
        Person person = personService.getPersonById(personId).get();
        Duty duty = new Duty(plant, person);

        dutyRepository.save(duty);
        return duty;
    }

    //  todo: INDEX
    public List<Duty> getAllDuties(){
        return dutyRepository.findAll();
    }

    // todo: SHOW
    public Optional<Duty> getDutyById(long id){
        return dutyRepository.findById(id);
    }

    // todo: partial UPDATE for plant only
    // assume: plant is created before updating
    public Duty updateDutyPlant(long id, long plantId){
        Plant plant = plantService.getPlantById(plantId).get();
        Duty duty = dutyRepository.findById(id).get();
        duty.setPlant(plant);
        dutyRepository.save(duty);
        return duty;
    }

    // todo: partial UPDATE for person only
    public Duty updateDutyPerson(long dutyId, long personId){
        Person person = personService.getPersonById(personId).get();
        Duty duty = dutyRepository.findById(dutyId).get();
        duty.setPerson(person);
        dutyRepository.save(duty);
        return duty;
    }

    // todo: full update
    public Duty updateDutyFull(long dutyId, long plantId, long personId){
        Plant plant = plantService.getPlantById(plantId).get();
        Person person = personService.getPersonById(personId).get();
        Duty duty = dutyRepository.findById(dutyId).get();
        duty.setPlant(plant);
        duty.setPerson(person);
        dutyRepository.save(duty);
        return duty;
    }


    // todo: DELETE
    public void deleteDuty(long id){
        dutyRepository.deleteById(id);
    }

}
