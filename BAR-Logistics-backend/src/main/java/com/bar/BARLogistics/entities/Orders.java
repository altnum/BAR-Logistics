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
    private Users user_id;

    @Column(name = "order_date")
    private Date order_date;

    @Column(name = "ship_date")
    private Date ship_date;

    @Column(name = "status")
    private String status;

    @ManyToMany
    @JoinTable(name = "orders_parts", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "part_id"))
    private List<Parts> parts = new LinkedList<>();

    public Orders() {
    }

    public Orders(Users user, Date order_date, Date ship_date, String status) {
        this.user_id = user;
        this.order_date = order_date;
        this.ship_date = ship_date;
        this.status = status;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getShip_date() {
        return ship_date;
    }

    public void setShip_date(Date ship_date) {
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
