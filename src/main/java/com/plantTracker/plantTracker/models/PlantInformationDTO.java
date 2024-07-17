package com.plantTracker.plantTracker.models;

public class PlantInformationDTO {

    private long id;
    private String plantInfo;


    public PlantInformationDTO(long id, String plantInfo){
        this.id = id;
        this.plantInfo = plantInfo;
    }

    public PlantInformationDTO() {
    }

    public long getPlantId() {
        return id;
    }

    public void setPlantId(long id) {
        this.id = id;
    }

    public String getPlantInfo() {
        return plantInfo;
    }

    public void setPlantInfo(String plantInfo) {
        this.plantInfo = plantInfo;
    }
}
