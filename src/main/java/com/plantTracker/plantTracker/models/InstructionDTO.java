package com.plantTracker.plantTracker.models;

public class InstructionDTO {

    private Long id;
    private String instruction;

    public InstructionDTO(Long id, String instruction) {
        this.id = id;
        this.instruction = instruction;
    }

    public InstructionDTO() {
    }

    public Long getPlantId() {
        return id;
    }

    public void setPlantId(Long id) {
        this.id = id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
