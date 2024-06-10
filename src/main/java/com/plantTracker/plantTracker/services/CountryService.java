package com.plantTracker.plantTracker.services;

import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.CountryDTO;
import com.plantTracker.plantTracker.models.enums.Climate;
import com.plantTracker.plantTracker.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Country addNewCountry(CountryDTO countryDTO){

        String name = countryDTO.getName();
        Climate climate = Climate.valueOf(countryDTO.getClimate());

        Country country = new Country(name, climate);
        countryRepository.save(country);

        return country;
    }

    public Optional<Country> getCountryById(Long countryId){
        return countryRepository.findById(countryId);
    }
}
