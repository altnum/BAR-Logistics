package com.bar.BARLogistics.controllers;

import com.bar.BARLogistics.entities.Orders;
import com.bar.BARLogistics.entities.Orders_parts;
import com.bar.BARLogistics.repositories.OrdersRepository;
import com.bar.BARLogistics.repositories.Orders_partsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    Orders_partsRepository orders_partsRepository;

    @GetMapping("/all")
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @GetMapping("/all/detailed")
    public List<Orders_parts> getAllOrdersWithDetails() {
        return orders_partsRepository.findAll();
    }

}
