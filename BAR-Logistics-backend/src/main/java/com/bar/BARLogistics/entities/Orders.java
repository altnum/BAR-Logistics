package com.bar.BARLogistics.entities;

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

    @Column(name = "status")
    private String status;

    @ManyToMany
    @JoinTable(name = "orders_parts", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "part_id"))
    private List<Parts> parts = new LinkedList<>();

    public Orders() {
    }

    public Orders(Integer order_id, Users user, String order_date, String ship_date, String status, List<Parts> parts) {
        this.order_id = order_id;
        this.user = user;
        this.order_date = order_date;
        this.ship_date = ship_date;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Parts> getParts() {
        return parts;
    }

    public void setParts(List<Parts> parts) {
        this.parts = parts;
    }
}
