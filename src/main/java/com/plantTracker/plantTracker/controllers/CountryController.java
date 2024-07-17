package com.plantTracker.plantTracker.controllers;

import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.CountryDTO;
import com.plantTracker.plantTracker.models.Duty;
import com.plantTracker.plantTracker.models.enums.Climate;
import com.plantTracker.plantTracker.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value= "/countries")
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        Optional<Country> countryOptional = countryService.getCountryById(id);
        if (countryOptional.isPresent()) {
            return new ResponseEntity<>(countryOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Country> addNewCountry(@RequestBody CountryDTO countryDTO) {
        Country country = countryService.addNewCountry(countryDTO);
        if (Objects.isNull(country)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(country, HttpStatus.CREATED);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Country> updateDutyPlant(@PathVariable Long id, @RequestBody Map<String, String> updatePayload) {
        Optional<Country> countryOptional = countryService.getCountryById(id);
        if (countryOptional.isPresent()) {
            if (!Objects.isNull(updatePayload.get("name"))) {
                Country country = countryService.updateCountryName(id, updatePayload.get("name"));
                return new ResponseEntity<>(country, HttpStatus.OK);

            } else if (!Objects.isNull(updatePayload.get("climate"))) {
                Climate climate = Climate.valueOf(updatePayload.get("climate"));
                Country country = countryService.updateCountryClimate(id, climate);
                return new ResponseEntity<>(country, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Country> fullUpdateCountry(@RequestBody CountryDTO countryDTO, @PathVariable Long id){
        Optional<Country> countryOptional = countryService.getCountryById(id);
        if (countryOptional.isPresent()) {
            if (!Objects.isNull(countryDTO)) {
                Country country = countryService.fullUpdateCountry(countryDTO, id);
                return new ResponseEntity<>(country, HttpStatus.OK);

            }  else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

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