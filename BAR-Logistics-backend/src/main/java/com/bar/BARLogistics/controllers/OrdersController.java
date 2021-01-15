package com.bar.BARLogistics.controllers;

import com.bar.BARLogistics.entities.*;
import com.bar.BARLogistics.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
            Date date = new Date(System.currentTimeMillis());
            String date1 = formatter.format(date);

            Date shipDate = new Date(date.getTime() + 3600 * (distance / vehicle.get().getType().getAvg_speed() * 1000));
            ordersRepository.changeOrderDate(orderId, date1);
            ordersRepository.changeShipDate(orderId, formatter.format(shipDate));
        }
    }

    @GetMapping("user/orders/myyorders")
    public List<Orders> findCurrUserOrders(@RequestParam Integer userId) {
        return ordersRepository.findCurrUserOrders(Long.valueOf(userId));
    }

    @GetMapping("user/orders/myorders")
    public ResponseEntity<?> paginateMyOrders
            (@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
             @RequestParam(value = "perPage", defaultValue = "5") int perPage,
             @RequestParam Long userId){

        Pageable pageable = PageRequest.of(currentPage -1, perPage);
        Page<Orders> myOrders = ordersRepository.findMyPageOrders(pageable, userId);

        Map<String, Object> response = new HashMap<>();
        response.put("result", myOrders.getContent());
        response.put("currentPage", myOrders.getNumber());
        response.put("totalItems", myOrders.getTotalElements());
        response.put("totalPages", myOrders.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
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

    @GetMapping("user/orders/getorderprice")
    public Double returnOrdersPartsPrice(@RequestParam BigInteger part_id, @RequestParam Integer quantity) {
        Double calculatePrice;
        calculatePrice = partsRepository.findPartsByPart_num(part_id).getPrice() * quantity;

        return  calculatePrice;
    }

    @PostMapping("/admin/orders/delivered")
    public void deliverOrder (@RequestParam Integer orderId) {
        if ("processing".equals(ordersRepository.findById(orderId).get().getStatus())) {
            ordersRepository.changeStatus(orderId, "delivered");
            vehicleInventoryRepository.freeToGo(orderId);
        }
    }

    @GetMapping("/admin/orders/search/pages")
    public ResponseEntity<?> paginateOrders
            (@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
             @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        Pageable pageable = PageRequest.of(currentPage -1, perPage);
        Page<Orders> orders = ordersRepository.findPageOrders(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("result", orders.getContent());
        response.put("currentPage", orders.getNumber());
        response.put("totalItems", orders.getTotalElements());
        response.put("totalPages", orders.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
