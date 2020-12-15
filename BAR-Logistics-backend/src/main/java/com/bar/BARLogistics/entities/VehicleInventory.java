package com.bar.BARLogistics.entities;


import javax.persistence.*;

@Entity
@Table(name="vehicle_inventory")
public class VehicleInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "type")
    private VehicleInfo type;

    @Column (name = "availability")
    private Boolean availability;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VehicleInfo getType() {
        return type;
    }

    public void setType(VehicleInfo type) {
        this.type = type;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
