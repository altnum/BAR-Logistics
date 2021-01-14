package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.Orders;
<<<<<<< HEAD
import com.bar.BARLogistics.entities.Parts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
import com.bar.BARLogistics.entities.Orders_parts;
>>>>>>> 38828369c16afa02854a3eca6d556cbdd1625153
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

import java.math.BigInteger;
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

<<<<<<< HEAD
    @Query("SELECT o FROM Orders o")
    Page<Orders> findPageOrders(Pageable page);
=======
    @Query("SELECT o FROM Orders_parts o where o.order_id = :orderId")
    List<Orders_parts> getOrdersWithVolume (Integer orderId);
>>>>>>> 38828369c16afa02854a3eca6d556cbdd1625153
}
