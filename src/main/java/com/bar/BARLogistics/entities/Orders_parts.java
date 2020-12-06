package com.bar.BARLogistics.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders_parts")
public class Orders_parts implements Serializable {
    @Id
    @Column(name = "part_id")
    private Integer part_id;

    @Column(name = "order_id")
    private Integer order_id;

    @Column(name = "quantity")
    private Integer quantity;

    public Orders_parts() {
    }

    public Orders_parts(Integer order_id, Integer part_id, Integer quantity) {
        this.order_id = order_id;
        this.part_id = part_id;
        this.quantity = quantity;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getPart_id() {
        return part_id;
    }

    public void setPart_id(Integer part_id) {
        this.part_id = part_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
