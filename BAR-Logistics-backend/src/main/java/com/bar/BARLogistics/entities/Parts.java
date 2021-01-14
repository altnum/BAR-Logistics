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

    @ManyToOne
    @JoinColumn(name = "location")
    private PartsLocations location;

    @Column(name = "price")
    private double price;

    @Column(name = "volume")
    private Integer volume;

    public Parts() {
    }

    public Parts(BigInteger part_num) {
        this.part_num = part_num;
    }

    public Parts(String part_name, PartsLocations location, double price, Integer volume) {
        this.part_name = part_name;
        this.location = location;
        this.price = price;
        this.volume = volume;
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

    public PartsLocations getLocation() {
        return location;
    }

    public void setLocation(PartsLocations location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
