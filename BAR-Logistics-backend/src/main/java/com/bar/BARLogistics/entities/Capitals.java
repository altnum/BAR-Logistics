package com.bar.BARLogistics.entities;

import javax.persistence.*;

@Entity
@Table(name="capitals")
public class Capitals {

    @Id
    @Column (name = "name")
    private String name;

    @Column (name = "distance_from_bar")
    private Integer distance_from_bar;

    @Column (name = "import_tax")
    private double import_tax;

    public double getImport_tax() {
        return import_tax;
    }

    public void setImport_tax(double import_tax) {
        this.import_tax = import_tax;
    }

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
