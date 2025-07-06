package org.example.orders.controller;

import org.example.orders.model.Order;
import org.example.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        orderService.create(order);
        return ResponseEntity.ok("Ордер создан и успешно отправлен");
    }


}
