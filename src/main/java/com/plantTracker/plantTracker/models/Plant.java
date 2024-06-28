package com.plantTracker.plantTracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plantTracker.plantTracker.models.enums.Priority;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "priority")
    private Priority priority;

    @Column(name = "last_watered")
    private List<String> lastWateredDates;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @JsonIgnoreProperties({"plants"})
    private Country country;

    @OneToMany(mappedBy = "plant")
    @JsonIgnoreProperties({"plant"})
    private List<Duty> duties;

    @Column(name = "interval_between_watering")
    protected int intervalBetweenWatering;



    public Plant(String name, int age, Priority priority, Country country) {
        this.name = name;
        this.age = age;
        this.priority = priority;
        this.lastWateredDates = new ArrayList<>();
        this.country = country;
        this.duties = new ArrayList<>();
        this.intervalBetweenWatering = 2;
    }
    public Plant(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public List<String> getLastWateredDates() {
        return lastWateredDates;
    }

    public void setLastWateredDates(List<String> lastWateredDates) {
        this.lastWateredDates = lastWateredDates;
    }

    public void addToLastWateredDates(String newDate){
        this.lastWateredDates.add(newDate);
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Duty> getDuties() {
        return duties;
    }

    public void setDuties(List<Duty> duties) {
        this.duties = duties;
    }

    public int getIntervalBetweenWatering() {
        return intervalBetweenWatering;
    }

    public void setIntervalBetweenWatering(int intervalBetweenWatering) {
        this.intervalBetweenWatering = intervalBetweenWatering;
    }

    public String provideInstruction(){
        return "Be mindful when watering!";

    }
}
