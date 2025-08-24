package com.nttdata.dio.orderservice.factory;

import com.nttdata.dio.orderservice.dto.CreateOrderRequest;
import com.nttdata.dio.orderservice.model.Order;

public interface OrderFactory {
    Order createOrder(CreateOrderRequest request);
}
