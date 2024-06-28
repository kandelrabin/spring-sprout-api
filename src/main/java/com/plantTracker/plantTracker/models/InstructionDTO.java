package com.plantTracker.plantTracker.models;

public class InstructionDTO {

    private Long plantId;
    private String instruction;

    public InstructionDTO(Long plantId, String instruction) {
        this.plantId = plantId;
        this.instruction = instruction;
    }

    public InstructionDTO() {
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
