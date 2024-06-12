package com.plantTracker.plantTracker.models.plantChild;

import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.Interfaces.Flowerable;
import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.models.enums.Climate;
import com.plantTracker.plantTracker.models.enums.Condition;
import com.plantTracker.plantTracker.models.enums.Priority;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Succulents extends Plant implements Flowerable {

    private String name;
    private Priority priority;
    private Country country;
    private int intervalBetweenWatering;
    private Boolean isBlooming;


    public Succulents(String name, Priority priority, Country country){
        super(name, priority, country);
//        this.intervalBetweenWatering = 10;
        this.setIntervalBetweenWatering(10);

    }

    public Succulents() {
    }

    @Override
    public Boolean getBlooming() {
        return isBlooming;
    }

    @Override
    public void setBlooming(Boolean isBlooming) {
        this.isBlooming = isBlooming;
    }

    public String provideInstruction(){
       Condition condition = Climate.DRY.getCondition();
       int minTemperature = condition.getMinTemperature();
       int maxTemperature = condition.getMaxTemperature();
       int minHumidity = condition.getMinHumidity();
       int maxHumidity = condition.getMaxHumidity();


       String interval =  String.valueOf(this.getIntervalBetweenWatering());
       String priority = String.valueOf(this.getPriority());
       String message = String.format("Best condition for succulents is [Min Temperature, Max Temperature]: %d, %d ,[Min Humidity, Max Humidity]: %d, %d" +
               " The watering interval is %s. The watering priority is %s." +
               " Watch out for the spikes.",minTemperature,maxTemperature, minHumidity, maxHumidity, interval, priority);
       return message;
    }


}
