package com.plantTracker.plantTracker.models;

import com.plantTracker.plantTracker.models.enums.Priority;

public class PlantDTO {

    private String name;
    private String priority;
    private String lastWatered;
    private long countryId;


    public PlantDTO(String name, String priority, String lastWatered, long countryId) {
        this.name = name;
        this.priority = priority;
        this.lastWatered = lastWatered;
        this.countryId = countryId;
    }

    public PlantDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(String lastWatered) {
        this.lastWatered = lastWatered;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }
}
