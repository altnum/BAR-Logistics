package com.bar.BARLogistics.controllers;

import com.bar.BARLogistics.entities.Orders;
import com.bar.BARLogistics.entities.Orders_parts;
import com.bar.BARLogistics.repositories.OrdersRepository;
import com.bar.BARLogistics.repositories.Orders_partsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("orders")
public class OrdersController {

    private final OrdersRepository ordersRepository;
    private final Orders_partsRepository orders_partsRepository;

    public OrdersController(OrdersRepository ordersRepository, Orders_partsRepository orders_partsRepository) {
        this.ordersRepository = ordersRepository;
        this.orders_partsRepository = orders_partsRepository;
    }

    @GetMapping("/all")
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @GetMapping("/all/detailed")
    public List<Orders_parts> getAllOrdersWithDetails() {
        return orders_partsRepository.findAll();
    }

}
