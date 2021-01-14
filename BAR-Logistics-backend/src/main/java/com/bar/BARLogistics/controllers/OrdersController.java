package com.bar.BARLogistics.controllers;

import com.bar.BARLogistics.entities.*;
import com.bar.BARLogistics.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class OrdersController {

    private  OrdersRepository ordersRepository;
    private  Orders_partsRepository orders_partsRepository;
    private UserRepository userRepository;
    private PartsRepository partsRepository;
    private VehicleInventoryRepository vehicleInventoryRepository;

    public OrdersController(OrdersRepository ordersRepository, Orders_partsRepository orders_partsRepository, UserRepository userRepository, PartsRepository partsRepository, VehicleInventoryRepository vehicleInventoryRepository) {
        this.ordersRepository = ordersRepository;
        this.orders_partsRepository = orders_partsRepository;
        this.userRepository = userRepository;
        this.partsRepository = partsRepository;
        this.vehicleInventoryRepository = vehicleInventoryRepository;
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

        String date1 = String.valueOf(LocalDateTime.now());
        date1 = date1.replace("T", " ");
        int toCutTo1 = date1.indexOf(".");
        date1 = date1.substring(0, toCutTo1);

        //Изчисляване на деня на очакваната доставка
        List<Parts> listToAdd = new ArrayList<>(setToSave);

        String date2 = String.valueOf(LocalDateTime.now());
        date2 = date2.replace("T", " ");
        int toCutTo2 = date2.indexOf(".");
        date2 = date2.substring(0, toCutTo2);

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

    @PostMapping("/admin/orders/adminSend")
    public void sendOrder(@RequestParam Integer orderId, @RequestParam Integer vehicleId) throws ParseException {
        if ("pending".equals(ordersRepository.findById(orderId).get().getStatus())) {
            ordersRepository.changeStatus(orderId, "processing");
            vehicleInventoryRepository.changeStatus(false, vehicleId);
            vehicleInventoryRepository.attachOrder(orderId, vehicleId);
            Integer distance = ordersRepository.findById(orderId).get().getUser_id().getAddress().getDistance_from_bar();
            Optional<VehicleInventory> vehicle = vehicleInventoryRepository.findById(vehicleId);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = ordersRepository.findById(orderId).get().getOrder_date();
            Date date1 = formatter.parse(date);

            Date shipDate = new Date(date1.getTime() + 3600 * (distance / vehicle.get().getType().getAvg_speed() * 1000));

            ordersRepository.changeShipDate(orderId, formatter.format(shipDate));
        }
    }

    @GetMapping("user/orders/myorders")
    public List<Orders> findCurrUserOrders(@RequestParam Integer userId) {
        return ordersRepository.findCurrUserOrders(Long.valueOf(userId));
    }

    @GetMapping("admin/orders/getordervolume")
    public Integer returnOrdersWithVolume(@RequestParam Integer order_id) {
        List<Orders_parts> calculateVolume;
        calculateVolume = ordersRepository.getOrdersWithVolume(order_id);

        int currentVolume = 0;

        for (Orders_parts orders_parts : calculateVolume) {
            Parts currentPart = partsRepository.findPartsByPart_num(BigInteger.valueOf(orders_parts.getPart_id()));
            currentVolume += currentPart.getVolume() * orders_parts.getQuantity();
        }
        return  currentVolume;
    }

    @PostMapping("/admin/orders/delivered")
    public void deliverOrder (@RequestParam Integer orderId) {
        if ("processing".equals(ordersRepository.findById(orderId).get().getStatus())) {
            ordersRepository.changeStatus(orderId, "delivered");
            vehicleInventoryRepository.freeToGo(orderId);
        }
    }

}
