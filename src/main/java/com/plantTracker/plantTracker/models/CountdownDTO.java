package com.plantTracker.plantTracker.models;

public class CountdownDTO {

    private Long plantId;
    private String countdown;

    public CountdownDTO( Long plantId,String countdown) {
        this.countdown = countdown;
        this.plantId = plantId;
    }

    public CountdownDTO() {
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public String getCountdown() {
        return countdown;
    }

    public void setCountdown(String countdown) {
        this.countdown = countdown;
    }
}
