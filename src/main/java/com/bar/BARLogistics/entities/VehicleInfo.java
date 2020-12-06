package com.bar.BARLogistics.entities;


import javax.persistence.*;

@Entity
@Table(name="vehicle_info")
public class VehicleInfo {

    @Id
    @Column (name = "type")
    private String type;

    @Column (name = "fuel_consumption")
    private Integer fuel_consumption;

    @Column (name = "avg_speed")
    private Integer avg_speed;

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

    public Integer getAvg_speed() {
        return avg_speed;
    }

    public void setAvg_speed(Integer avg_speed) {
        this.avg_speed = avg_speed;
    }
}
