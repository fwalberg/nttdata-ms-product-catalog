package com.nttdata.dio.orderservice.service;

import com.nttdata.dio.orderservice.dto.CreateOrderRequest;
import com.nttdata.dio.orderservice.enuns.OrderType;
import com.nttdata.dio.orderservice.model.Order;
import com.nttdata.dio.orderservice.repository.Productclient;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderFactoryProvider factoryProvider;

    public OrderService(OrderFactoryProvider factoryProvider ) {
        this.factoryProvider = factoryProvider;
    }

    public Order createOrder(OrderType orderType, CreateOrderRequest request) {
        return factoryProvider.createOrder(orderType, request);
    }

    public Order createStandardOrder(CreateOrderRequest request) {
        return createOrder(OrderType.STANDARD, request);
    }

    public Order createExpressOrder(CreateOrderRequest request) {
        return createOrder(OrderType.EXPRESS, request);
    }
}
