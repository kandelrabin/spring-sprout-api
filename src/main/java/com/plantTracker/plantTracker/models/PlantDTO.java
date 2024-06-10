package com.plantTracker.plantTracker.models;

import com.plantTracker.plantTracker.models.enums.Priority;

public class PlantDTO {

    private String name;
    private Priority priority;
    private String lastWatered;
    private Long countryId;


    public PlantDTO(String name, Priority priority, String lastWatered, Long countryId) {
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(String lastWatered) {
        this.lastWatered = lastWatered;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
