package com.plantTracker.plantTracker.services;

import com.plantTracker.plantTracker.models.Duty;
import com.plantTracker.plantTracker.models.Person;
import com.plantTracker.plantTracker.repositories.DutyRepository;
import com.plantTracker.plantTracker.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DutyRepository dutyRepository;

    public Person addNewPerson(String name){
        Person person = new Person(name);
        personRepository.save(person);
        return person;
    }

    public Optional<Person> getPersonById(long id){
        return personRepository.findById(id);
    }

    public List<Person> getAllPeople(){
        return personRepository.findAll();
    }

    public Person updatePerson(long id, String name){
        Person personToUpdate = personRepository.findById(id).get();
        personToUpdate.setName(name);
        personRepository.save(personToUpdate);
        return personToUpdate;
    }

    public void deletePerson(long personId){
        Person person = getPersonById(personId).get();
        for (Duty duty : person.getDuties()){
            dutyRepository.deleteById(duty.getId());
            }
        personRepository.deleteById(personId);

    }


}












