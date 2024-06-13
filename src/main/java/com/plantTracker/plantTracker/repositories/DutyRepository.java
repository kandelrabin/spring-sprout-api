package com.plantTracker.plantTracker.repositories;

import com.plantTracker.plantTracker.models.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DutyRepository extends JpaRepository<Duty, Long> {

    List<Duty> findAllByPersonId(Long personId);

    List<Duty> findAllByPlantId(Long plantId);
}
