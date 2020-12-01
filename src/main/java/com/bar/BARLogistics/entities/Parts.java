package com.bar.BARLogistics.entities;

import javax.persistence.*;

@Table(name="parts")
public class Parts {

    @Column(name = "part_num")
    private Integer part_num;

    @Column(name = "part_name")
    private String part_name;

    @Column(name = "vehicle_id")
    private Integer vehicle_id;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private double price;

    public Integer getPart_num() {
        return part_num;
    }

    public void setPart_num(Integer part_num) {
        this.part_num = part_num;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
