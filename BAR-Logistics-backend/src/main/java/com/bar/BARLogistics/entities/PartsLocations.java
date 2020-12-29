package com.bar.BARLogistics.entities;


import javax.persistence.*;

@Entity
@Table(name="parts_locations")
public class PartsLocations {

    @Id
    @Column (name = "name")
    private String name;

    @Column (name = "distances_from_bar")
    private Integer distances_from_bar;

    public PartsLocations() {
    }

    public PartsLocations(String name, Integer distances_from_bar) {
        this.name = name;
        this.distances_from_bar = distances_from_bar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistances_from_bar() {
        return distances_from_bar;
    }

    public void setDistances_from_bar(Integer distances_from_bar) {
        this.distances_from_bar = distances_from_bar;
    }
}
