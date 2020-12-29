package com.bar.BARLogistics.controllers;

import com.bar.BARLogistics.entities.*;
import com.bar.BARLogistics.repositories.OrdersRepository;
import com.bar.BARLogistics.repositories.Orders_partsRepository;
import com.bar.BARLogistics.repositories.PartsRepository;
import com.bar.BARLogistics.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class OrdersController {

    private  OrdersRepository ordersRepository;
    private  Orders_partsRepository orders_partsRepository;
    private UserRepository userRepository;
    private PartsRepository partsRepository;

    public OrdersController(OrdersRepository ordersRepository, Orders_partsRepository orders_partsRepository, UserRepository userRepository, PartsRepository partsRepository) {
        this.ordersRepository = ordersRepository;
        this.orders_partsRepository = orders_partsRepository;
        this.userRepository = userRepository;
        this.partsRepository = partsRepository;
    }

    @GetMapping("/admin/orders/all")
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @GetMapping("/admin/orders/all/detailed")
    public List<Orders_parts> getAllOrdersWithDetails(Integer order_id) {
        return orders_partsRepository.findAllByOrderId(order_id);
    }

    @PostMapping("/user/orders/save")
    public ResponseEntity<?> placeOrder(@RequestParam String username, @RequestParam String parts, @RequestParam String price) {
        String[] orderedParts = parts.split(",");
        Set<Parts> setToSave = new LinkedHashSet<>();

        Map<String, Integer> partsRespectiveQuantity = new LinkedHashMap<>();

        for (String str : orderedParts) {
            Parts part = partsRepository.findPartsByPart_num(BigInteger.valueOf(Long.parseLong(str)));
            setToSave.add(part);
        }

        for (String partNum : orderedParts) {
            partsRespectiveQuantity.putIfAbsent(partNum, 0);
            partsRespectiveQuantity.put(partNum, partsRespectiveQuantity.get(partNum) + 1);
        }

        Users users = userRepository.findUserByUsername(username);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date1));

        //Изчисляване на деня на очакваната доставка
        List<Parts> listToAdd = new ArrayList<>(setToSave);
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date2));

        String status = "pending";

        Orders orders = new Orders(users, date1, date2, status, Double.parseDouble(price));

        orders.setParts(listToAdd);

        orders = ordersRepository.save(orders);

        for (Map.Entry<String, Integer> entry : partsRespectiveQuantity.entrySet()) {
            orders_partsRepository.setOrders_partsQuantityByOrderAndPartId(entry.getValue(), orders.getOrder_id(), Integer.parseInt(entry.getKey()));
        }

        Map<String, Object> response = new HashMap<>();

        response.put("message", "Успешно направена поръчка!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
