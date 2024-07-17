package com.plantTracker.plantTracker.models;

public class CountdownDTO {

    private Long id;
    private String countdown;

    public CountdownDTO( Long id,String countdown) {
        this.countdown = countdown;
        this.id = id;
    }

    public CountdownDTO() {
    }

    public Long getPlantId() {
        return id;
    }

    public void setPlantId(Long id) {
        this.id = id;
    }

    public String getCountdown() {
        return countdown;
    }

    public void setCountdown(String countdown) {
        this.countdown = countdown;
    }
}
