package com.nttdata.dio.orderservice.model;

import com.nttdata.dio.orderservice.enuns.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Order {
    private String id;
    private String customerId;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private OffsetDateTime createdAt;
    private OrderStatus status;

    public Order() {
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Order item cannot be null.");
        }
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public BigDecimal getTotalAmount() {
        return items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
