package com.plantTracker.plantTracker.models.plantChild;

import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.models.enums.Priority;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Succulents extends Plant {

    private String name;
    private Priority priority;
    private Country country;
    private int intervalBetweenWatering;


    public Succulents(String name, Priority priority, Country country){
        super(name, priority, country);
//        this.intervalBetweenWatering = 10;
        this.setIntervalBetweenWatering(10);

    }

    public Succulents() {
    }
}
