package com.plantTracker.plantTracker.repositories;

import com.plantTracker.plantTracker.models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}
