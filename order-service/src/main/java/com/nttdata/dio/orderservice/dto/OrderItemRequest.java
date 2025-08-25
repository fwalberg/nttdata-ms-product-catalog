package com.nttdata.dio.orderservice.dto;

import java.math.BigDecimal;

public record OrderItemRequest(
        String orderItemId,
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal unitPrice
) {
}
