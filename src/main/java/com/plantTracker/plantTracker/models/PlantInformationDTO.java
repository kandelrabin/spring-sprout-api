package com.plantTracker.plantTracker.models;

public class PlantInformationDTO {

    private long plantId;
    private String plantInfo;


    public PlantInformationDTO(long plantId, String plantInfo){
        this.plantId = plantId;
        this.plantInfo = plantInfo;
    }

    public PlantInformationDTO() {
    }

    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }

    public String getPlantInfo() {
        return plantInfo;
    }

    public void setPlantInfo(String plantInfo) {
        this.plantInfo = plantInfo;
    }
}
