package com.bar.BARLogistics.entities;


import javax.persistence.*;

@Table(name="vehicle_info")
public class VehicleInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "type")
    private String type;

    @Column (name = "free_to_go")
    private Boolean free_to_go;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getFree_to_go() {
        return free_to_go;
    }

    public void setFree_to_go(Boolean free_to_go) {
        this.free_to_go = free_to_go;
    }
}
