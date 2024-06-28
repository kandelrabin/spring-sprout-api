package com.plantTracker.plantTracker.services;

import com.plantTracker.plantTracker.models.*;
import com.plantTracker.plantTracker.models.enums.Climate;
import com.plantTracker.plantTracker.repositories.CountryRepository;
import com.plantTracker.plantTracker.repositories.DutyRepository;
import com.plantTracker.plantTracker.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    DutyRepository dutyRepository;

    public Country addNewCountry(CountryDTO countryDTO){
        String name = countryDTO.getName();
        Climate climate = Climate.valueOf(countryDTO.getClimate());

        Country country = new Country(name, climate);
        countryRepository.save(country);
        return country;
    }

    public Optional<Country> getCountryById(long id){
        return countryRepository.findById(id);
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    public Country updateCountryName(long countryId, String name){
        Country updateNameCountry = getCountryById(countryId).get();
        updateNameCountry.setName(name);

        countryRepository.save(updateNameCountry);
        return updateNameCountry;
    }

    public Country updateCountryClimate(long countryId, Climate climate){
        Country updateClimateCountry = getCountryById(countryId).get();
        updateClimateCountry.setClimate(climate);

        countryRepository.save(updateClimateCountry);
        return updateClimateCountry;
    }

    public Country fullUpdateCountry(CountryDTO countryDTO, long id){
        Country countryToFullUpdate = getCountryById(id).get();

        countryToFullUpdate.setName(countryDTO.getName());

        Climate climate = Climate.valueOf(countryDTO.getClimate());
        countryToFullUpdate.setClimate(climate);

        countryRepository.save(countryToFullUpdate);
        return countryToFullUpdate;
    }

    public void deleteCountry(long countryId){
        Country country = countryRepository.findById(countryId).get();
        for (Plant plant : country.getPlants()){
            deletePlantFromCountry(plant.getId());
            }
        countryRepository.deleteById(countryId);
    }

    public void deletePlantFromCountry(long id){
        Plant plant = plantRepository.findById(id).get();
        for (Duty duty : plant.getDuties()){
            dutyRepository.deleteById(duty.getId());
        }
        plantRepository.deleteById(id);

    }

}
