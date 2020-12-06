package com.bar.BARLogistics.entities;

import com.bar.BARLogistics.repositories.OrdersRepository;
import com.bar.BARLogistics.repositories.Orders_partsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "order_date")
    private String order_date;

    @Column(name = "ship_date")
    private String ship_date;

    @Column(name = "finished")
    private boolean finished;

    @Column(name = "vehicle_type")
    private String vehicle_type;

    @Column(name = "vehicle_id")
    private Integer vehicle_id;

    @ManyToMany
    @JoinTable(name = "orders_parts", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "part_id"))
    private List<Parts> parts = new LinkedList<>();

    public Orders() {
    }

    public Orders(Integer order_id, Users user, String order_date, String ship_date, boolean finished, String vehicle_type, Integer vehicle_id, List<Parts> parts) {
        this.order_id = order_id;
        this.user = user;
        this.order_date = order_date;
        this.ship_date = ship_date;
        this.finished = finished;
        this.vehicle_type = vehicle_type;
        this.vehicle_id = vehicle_id;
        this.parts = parts;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getShip_date() {
        return ship_date;
    }

    public void setShip_date(String ship_date) {
        this.ship_date = ship_date;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public List<Parts> getParts() {
        return parts;
    }

    public void setParts(List<Parts> parts) {
       /* List<Parts> newParts = new ArrayList<>();

        for (int i = 0; i < this.quantities.size(); i++) {
            for (int j = 1; j < this.quantities.get(i).getQuantity(); j++) {
                newParts.add(this.parts.get(i));
            }
        }

        this.parts = Stream.concat(parts.stream(), newParts.stream()).collect(Collectors.toList());

        */

        this.parts = parts;
    }
}
