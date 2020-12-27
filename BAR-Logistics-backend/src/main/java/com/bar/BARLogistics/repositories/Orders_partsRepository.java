package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.Orders_parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface Orders_partsRepository extends JpaRepository<Orders_parts, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Orders_parts SET quantity = :quantity WHERE order_id = :order_id AND part_id = :part_id")
    void setOrders_partsQuantityByOrderAndPartId(Integer quantity, Integer order_id, Integer part_id);

    @Query("SELECT o_l FROM Orders_parts o_l WHERE o_l.order_id = :order_id")
    List<Orders_parts> findAllByOrderId(Integer order_id);
}
