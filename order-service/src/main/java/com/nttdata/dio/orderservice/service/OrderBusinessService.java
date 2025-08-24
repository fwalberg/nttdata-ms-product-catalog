package com.nttdata.dio.orderservice.service;

import com.nttdata.dio.orderservice.dto.CreateOrderRequest;
import com.nttdata.dio.orderservice.dto.OrderItemRequest;
import com.nttdata.dio.orderservice.model.Order;
import com.nttdata.dio.orderservice.model.OrderItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrderBusinessService {

    public void validateOrderCreation(CreateOrderRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Order request cannot be null");
        }

        if (request.customerId() == null || request.customerId().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID is required");
        }

        if (request.items() == null || request.items().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        request.items().forEach(this::validateOrderItem);
    }

    private void validateOrderItem(OrderItemRequest item) {
        if (item.productId() == null || item.productId() <= 0) {
            throw new IllegalArgumentException("Product ID is required");
        }

        if (item.quantity() == null || item.quantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }

    public BigDecimal calculateOrderTotal(List<OrderItem> items) {
        return items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public boolean isValidForProcessing(Order order) {
        return order != null
                && order.getCustomerId() != null
                && !order.getItems().isEmpty()
                && order.getTotalAmount().compareTo(BigDecimal.ZERO) > 0;
    }
}
