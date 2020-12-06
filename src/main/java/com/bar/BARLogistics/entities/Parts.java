package com.bar.BARLogistics.entities;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="parts")
public class Parts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_num")
    private BigInteger part_num;

    @Column(name = "part_name")
    private String part_name;

    @Column(name = "vehicle_type")
    private String vehicle_type;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private double price;

    public Parts() {
    }

    public Parts(BigInteger part_num) {
        this.part_num = part_num;
    }

    public Parts(BigInteger part_num, String part_name, String vehicle_type, String location, double price) {
        this.part_num = part_num;
        this.part_name = part_name;
        this.vehicle_type = vehicle_type;
        this.location = location;
        this.price = price;
    }

    public BigInteger getPart_num() {
        return part_num;
    }

    public void setPart_num(BigInteger part_num) {
        this.part_num = part_num;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
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
