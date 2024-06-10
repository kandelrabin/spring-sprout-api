package com.plantTracker.plantTracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Priority;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private Priority priority;

    @Column(name = "is_watered")
    private Boolean isWatered;

    @Column(name = "last_watered")
    private String lastWatered;

    @Column(name = "country")
    private Country country;

    @OneToMany(mappedBy = "plant")
    @JsonIgnoreProperties({"plant"})
    private List<Duty> duties;

    public Plant(long id, String name, Priority priority, Boolean isWatered, String lastWatered, Country country) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.isWatered = isWatered;
        this.lastWatered = lastWatered;
        this.country = country;
        this.duties = new ArrayList<>();
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Boolean getWatered() {
        return isWatered;
    }

    public void setWatered(Boolean watered) {
        isWatered = watered;
    }

    public String getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(String lastWatered) {
        this.lastWatered = lastWatered;
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
}
