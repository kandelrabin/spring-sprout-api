package com.plantTracker.plantTracker.components;


import com.plantTracker.plantTracker.models.*;
import com.plantTracker.plantTracker.services.CountryService;
import com.plantTracker.plantTracker.services.DutyService;
import com.plantTracker.plantTracker.services.PersonService;
import com.plantTracker.plantTracker.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PlantService plantService;

    @Autowired
    PersonService personService;

    @Autowired
    DutyService dutyService;

    @Autowired
    CountryService countryService;


    public void run(ApplicationArguments args) throws Exception{

        CountryDTO countryDTO = new CountryDTO("Egypt","DRY");
        Country egypt = countryService.addNewCountry(countryDTO);

        CountryDTO countryDTO2 = new CountryDTO("USA","TROPICAL");
        Country usa = countryService.addNewCountry(countryDTO2);

        PlantDTO cactusDTO = new PlantDTO("Cactus", "LOW", 1, "succulents");
        plantService.addNewPlant(cactusDTO);

        PlantDTO daisyDTO = new PlantDTO("Daisy", "LOW", 2,"null");
        plantService.addNewPlant(daisyDTO);

        PlantDTO jasmineDTO = new PlantDTO("Jasmine", "HIGH", 2,"climbers");
        plantService.addNewPlant(jasmineDTO);

        Person aaron = new Person("Aaron");
        personService.addNewPerson("aaron");

        Person kate = new Person( "Kate");
        personService.addNewPerson("kate");

        DutyDTO monitor = new DutyDTO(1,1);
        dutyService.addNewDuty(monitor);

        DutyDTO adjust = new DutyDTO(2, 2);
        dutyService.addNewDuty(adjust);

    }

}









