package com.plantTracker.plantTracker.repositories;

import com.plantTracker.plantTracker.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
