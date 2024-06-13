package com.plantTracker.plantTracker.models.plantChild;

import com.plantTracker.plantTracker.models.Country;
import com.plantTracker.plantTracker.models.Interfaces.Iinstruction;
import com.plantTracker.plantTracker.models.Plant;
import com.plantTracker.plantTracker.models.enums.Climate;
import com.plantTracker.plantTracker.models.enums.Condition;
import com.plantTracker.plantTracker.models.enums.Priority;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Climbers extends Plant implements Iinstruction {


    public Climbers(String name, int age, Priority priority, Country country){
        super(name, age, priority, country);
        this.intervalBetweenWatering = 5;
    }

    public Climbers() {
    }

    public String provideInstruction(){
        Condition condition = Climate.DRY.getCondition();
        int minTemperature = condition.getMinTemperature();
        int maxTemperature = condition.getMaxTemperature();
        int minHumidity = condition.getMinHumidity();
        int maxHumidity = condition.getMaxHumidity();

        String interval =  String.valueOf(this.getIntervalBetweenWatering());
        String priority = String.valueOf(this.getPriority());

        String message = String.format("Best condition for succulents is [Min Temperature, Max Temperature]: %d, %d ,[Min Humidity, Max Humidity]: %d, %d." +
                " The watering interval is %s. The watering priority is %s." +
                " Be sure to trim. With these plants the sky is the limit!",minTemperature,maxTemperature, minHumidity, maxHumidity, interval, priority);
        return message;
    }
}
