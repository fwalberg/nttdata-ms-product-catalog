package com.nttdata.dio.orderservice.controller;

import com.nttdata.dio.orderservice.dto.CreateOrderRequest;
import com.nttdata.dio.orderservice.model.Order;
import com.nttdata.dio.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/standard")
    public ResponseEntity<Order> createStandardOrder(@RequestBody CreateOrderRequest request) {
        try {
            Order order = orderService.createStandardOrder(request);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/express")
    public ResponseEntity<Order> createExpressOrder(@RequestBody CreateOrderRequest request) {
        try {
            Order order = orderService.createExpressOrder(request);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
