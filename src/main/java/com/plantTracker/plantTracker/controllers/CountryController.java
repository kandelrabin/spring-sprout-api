package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.CountryDTO;
import com.plantTracker.plantTracker.models.Duty;
import com.plantTracker.plantTracker.models.DutyDTO;
import com.plantTracker.plantTracker.models.enums.Climate;
import com.plantTracker.plantTracker.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value= "/countries")
public class CountryController {

    @Autowired
    CountryService countryService;

    // INDEX: GET localhost:8080/countries
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // SHOW: GET - localhost:8080/countries/id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Country> getCountryById(@RequestParam Long id) {
        Optional<Country> countryOptional = countryService.getCountryById(id);
        if (countryOptional.isPresent()) {
            return new ResponseEntity<>(countryOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    // CREATE: POST localhost:8080/countries
    @PostMapping
    public ResponseEntity<Country> addNewCountry(@RequestBody CountryDTO countryDTO) {
        Country country = countryService.addNewCountry(countryDTO);
        return new ResponseEntity<>(country, HttpStatus.CREATED);
    }

    // PARTIAL UPDATE: PATCH - localhost:8080/countries/id
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Country> updateDutyPlant(@PathVariable Long id, @RequestBody Map<Optional<String>, Optional<String>> updatePayload) {

        if (updatePayload.get("name").isPresent()) {
            Country country = countryService.updateCountryName(id, updatePayload.get("name").get());
            return new ResponseEntity<>(country, HttpStatus.OK);

        } else if (updatePayload.get("climate").isPresent()) {
            Climate climate = Climate.valueOf(updatePayload.get("climate").get());
            Country country = countryService.updateCountryClimate(id, climate);
            return new ResponseEntity<>(country, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    // FULL UPDATE: PUT - localhost:8080/countries/id
    @PutMapping(value = "/{id}")    // ADDED
    public ResponseEntity<Country> fullUpdateCountry(@RequestBody CountryDTO countryDTO, @PathVariable Long id){
        Country country = countryService.fullUpdateCountry(countryDTO, id);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    // DELETE: DELETE - localhost:8080/countries/id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id){
        Optional<Country> countryOptional = countryService.getCountryById(id);
        if (countryOptional.isPresent()){
            countryService.deleteCountry(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}