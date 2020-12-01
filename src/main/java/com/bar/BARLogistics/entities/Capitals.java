package com.bar.BARLogistics.entities;

import javax.persistence.*;

@Table(name="capitals")
public class Capitals {

    @Column (name = "name")
    private String name;

    @Column (name = "distance_from_bar")
    private Integer distance_from_bar;

    @Column (name = "trans_continental")
    private Boolean trans_continental;

    @Column (name = "abroad")
    private Boolean abroad;

    public Boolean getTrans_continental() {
        return trans_continental;
    }

    public void setTrans_continental(Boolean trans_continental) {
        this.trans_continental = trans_continental;
    }

    public Boolean getAbroad() {
        return abroad;
    }

    public void setAbroad(Boolean abroad) {
        this.abroad = abroad;
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
