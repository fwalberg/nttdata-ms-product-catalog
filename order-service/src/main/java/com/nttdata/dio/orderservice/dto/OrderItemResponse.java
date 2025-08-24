package com.nttdata.dio.orderservice.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
    String orderItemId,
    Long productId,
    String productName,
    Integer quantity,
    BigDecimal unitPrice,
    BigDecimal totalPrice
) {
}
