package com.plantTracker.plantTracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name = "name")
    private String name;

    @OneToMany(mappedBy = "person")
    @JsonIgnoreProperties({"person"})
    private List<Duty> duties;

    public Person(String name) {
        this.name = name;
        this.duties = new ArrayList<>();
    }

    public Person() {
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

    public List<Duty> getDuties() {
        return duties;
    }

    public void setDuties(List<Duty> duties) {
        this.duties = duties;
    }
}
