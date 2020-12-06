package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
