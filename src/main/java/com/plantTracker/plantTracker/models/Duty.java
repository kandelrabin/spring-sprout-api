package com.plantTracker.plantTracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity
@Table(name = "duties")
public class Duty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "plant_id", nullable = false)
    @JsonIgnoreProperties({"duties"})
    private Plant plant;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    @JsonIgnoreProperties({"duties"})
    private Person person;

    public Duty(Plant plant, Person person) {
        this.plant = plant;
        this.person = person;
    }

    public Duty() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
