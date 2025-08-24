package com.nttdata.dio.orderservice.dto;

import com.nttdata.dio.orderservice.enuns.OrderStatus;
import com.nttdata.dio.orderservice.enuns.OrderType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record OrderResponse(
        String orderId,
        String customerId,
        List<OrderItemResponse> items,
        BigDecimal totalAmount,
        OffsetDateTime createdAt,
        OrderStatus status,
        OrderType orderType
) {
}
