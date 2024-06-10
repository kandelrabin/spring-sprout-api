package com.plantTracker.plantTracker.models.enums;

public enum Priority {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private int impact;

    Priority(int impact){
        this.impact = impact;
    }

    public int getImpact() {
        return impact;
    }

}
