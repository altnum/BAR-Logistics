package com.bar.BARLogistics.entities;


import javax.persistence.*;

@Table(name="vehicle_info")
public class VehicleInfo {

    @Column (name = "type")
    private String type;

    @Column (name = "fuel_consumption")
    private Integer fuel_consumption;

    @Column (name = "speed")
    private Integer speed;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFuel_consumption() {
        return fuel_consumption;
    }

    public void setFuel_consumption(Integer fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
