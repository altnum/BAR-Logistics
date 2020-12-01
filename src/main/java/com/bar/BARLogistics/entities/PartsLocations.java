package com.bar.BARLogistics.entities;


import javax.persistence.*;

@Table(name="parts_locations")
public class PartsLocations {

    @Column (name = "name")
    private String name;

    @Column (name = "distance_from_bar")
    private Integer distance_from_bar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistance_from_bar() {
        return distance_from_bar;
    }

    public void setDistance_from_bar(Integer distance_from_bar) {
        this.distance_from_bar = distance_from_bar;
    }
}
