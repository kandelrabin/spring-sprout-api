package com.plantTracker.plantTracker.models;

public class DutyDTO {

    private long plantId;
    private long personId;

    public DutyDTO(long plantId, long personId) {
        this.plantId = plantId;
        this.personId = personId;
    }

    public DutyDTO() {
    }

    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
