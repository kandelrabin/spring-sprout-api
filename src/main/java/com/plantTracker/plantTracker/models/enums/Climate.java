package com.plantTracker.plantTracker.models.enums;


public enum Climate {
    TROPICAL (new Condition(22, 35, 77, 88)),
    DRY(new Condition(39, 70, 0, 21)),
    TEMPERATE(new Condition(-3, 18, 30, 90)),
    CONTINENTAL(new Condition(-30, 10, 77, 88)),
    POLAR(new Condition(-40, 0, 0, 20));

    private Condition condition;

    Climate(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
