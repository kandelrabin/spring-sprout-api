package com.plantTracker.plantTracker.models;

public class CountryDTO {

  private String name;
  private String climate;

    public CountryDTO(String name, String climate) {
        this.name = name;
        this.climate = climate;
    }

    public CountryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }
}
