package com.plantTracker.plantTracker.repositories;

import com.plantTracker.plantTracker.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
