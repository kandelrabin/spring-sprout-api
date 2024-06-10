package com.plantTracker.plantTracker.repositories;

import com.plantTracker.plantTracker.models.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyRepository extends JpaRepository<Duty, Long> {
}
