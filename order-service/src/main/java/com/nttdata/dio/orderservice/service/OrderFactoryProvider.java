package com.nttdata.dio.orderservice.service;

import com.nttdata.dio.orderservice.dto.CreateOrderRequest;
import com.nttdata.dio.orderservice.enuns.OrderType;
import com.nttdata.dio.orderservice.factory.ExpressOrderFactory;
import com.nttdata.dio.orderservice.factory.OrderFactory;
import com.nttdata.dio.orderservice.factory.StandardOrderFactory;
import com.nttdata.dio.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderFactoryProvider {

    private final Map<OrderType, OrderFactory> factories;

    public OrderFactoryProvider(StandardOrderFactory standardFactory,
                                ExpressOrderFactory expressFactory) {
        this.factories = Map.of(
                OrderType.STANDARD, standardFactory,
                OrderType.EXPRESS, expressFactory
        );
    }

    public OrderFactory getFactory(OrderType orderType) {
        OrderFactory factory = factories.get(orderType);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported order type: " + orderType);
        }
        return factory;
    }

    public Order createOrder(OrderType orderType, CreateOrderRequest request) {
        return getFactory(orderType).createOrder(request);
    }
}