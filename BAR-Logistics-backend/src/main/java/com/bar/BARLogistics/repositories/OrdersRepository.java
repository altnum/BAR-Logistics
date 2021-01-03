package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;


public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Orders SET status = :status WHERE order_id = :orderId")
    void changeStatus (Integer orderId, String status);

    @Transactional
    @Modifying
    @Query("UPDATE Orders SET ship_date = :ship_date WHERE order_id = :orderId")
    void changeShipDate (Integer orderId, String ship_date);

    @Query("SELECT o FROM Orders o WHERE o.user_id.id = :userId")
    List<Orders> findCurrUserOrders (Long userId);
}
