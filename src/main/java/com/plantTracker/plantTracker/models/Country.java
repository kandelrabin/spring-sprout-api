package com.plantTracker.plantTracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plantTracker.plantTracker.models.enums.Climate;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "climate")
    private Climate climate;

    @OneToMany(mappedBy = "country")
    @JsonIgnoreProperties({"country"})
    private List<Plant> plants;

    public Country(String name, Climate climate) {
        this.name = name;
        this.climate = climate;
        this.plants = new ArrayList<>();
    }

    public Country() {
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

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
