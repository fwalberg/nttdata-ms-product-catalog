package com.nttdata.dio.orderservice.dto;

import com.nttdata.dio.orderservice.enuns.OrderType;

import java.util.List;

public record CreateOrderRequest(
        String customerId,
        List<OrderItemRequest> items,
        OrderType orderType
) {
}
