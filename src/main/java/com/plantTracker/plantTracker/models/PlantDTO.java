package com.plantTracker.plantTracker.models;

import com.plantTracker.plantTracker.models.enums.Priority;

public class PlantDTO {

    private String name;
    private String priority;
    private long countryId;


    public PlantDTO(String name, String priority, long countryId) {
        this.name = name;
        this.priority = priority;
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

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }
}
