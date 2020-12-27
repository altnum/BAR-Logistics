package com.bar.BARLogistics.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

public class OrderPartsId implements Serializable {
    private Integer order_id;

    private Integer part_id;

    public OrderPartsId() {
    }

    public OrderPartsId(Integer order_id, Integer part_id) {
        this.order_id = order_id;
        this.part_id = part_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPartsId accountId = (OrderPartsId) o;
        return order_id.equals(accountId.order_id) &&
                part_id.equals(accountId.part_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, part_id);
    }
}
