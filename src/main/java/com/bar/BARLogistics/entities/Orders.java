package com.bar.BARLogistics.entities;

import javax.persistence.*;

@Table(name="orders")
public class Orders {

    @Column(name = "order_id")
    private Integer order_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "products_id")
    private Integer products_id;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProducts_id() {
        return products_id;
    }

    public void setProducts_id(Integer products_id) {
        this.products_id = products_id;
    }
}
