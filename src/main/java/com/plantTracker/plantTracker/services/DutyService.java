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

    public Duty addNewDuty(DutyDTO dutyDTO){
        long plantId = dutyDTO.getPlantId();
        long personId = dutyDTO.getPersonId();
        Duty duty;

        // Checks if a row for plant id or person id already exist in the duties table.
        if (!(personExists(personId)||plantExists(plantId))){
            Plant plant = plantService.getPlantById(plantId).get();
            Person person = personService.getPersonById(personId).get();
            duty = new Duty(plant, person);
            dutyRepository.save(duty);
            return duty;
        } else {
            return null;
        }
    }

    public boolean personExists(long id){
        List<Duty> duties = dutyRepository.findAllByPersonId(id);
        return !duties.isEmpty();
    }

    public boolean plantExists(long id){
        List<Duty> duties = dutyRepository.findAllByPlantId(id);
        return !duties.isEmpty();
    }

    public List<Duty> getAllDuties(){
        return dutyRepository.findAll();
    }

    public Optional<Duty> getDutyById(long id){
        return dutyRepository.findById(id);
    }


    public Duty updateDutyPlant(long id, long plantId){
        // Assumption: Plant with plantId exists in plants table in database.
        Plant plant = plantService.getPlantById(plantId).get();

        Duty duty = dutyRepository.findById(id).get();
        duty.setPlant(plant);
        dutyRepository.save(duty);
        return duty;
    }

    public Duty updateDutyPerson(long dutyId, long personId){
        Person person = personService.getPersonById(personId).get();
        Duty duty = dutyRepository.findById(dutyId).get();
        duty.setPerson(person);
        dutyRepository.save(duty);
        return duty;
    }

    public Duty updateDutyFull(long dutyId, long plantId, long personId){
        Plant plant = plantService.getPlantById(plantId).get();
        Person person = personService.getPersonById(personId).get();
        Duty duty = dutyRepository.findById(dutyId).get();
        duty.setPlant(plant);
        duty.setPerson(person);
        dutyRepository.save(duty);
        return duty;
    }

    public void deleteDuty(long id){
        dutyRepository.deleteById(id);
    }

}
