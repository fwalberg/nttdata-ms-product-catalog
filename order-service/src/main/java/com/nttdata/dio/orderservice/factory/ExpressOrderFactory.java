package com.nttdata.dio.orderservice.factory;

import com.nttdata.dio.orderservice.dto.CreateOrderRequest;
import com.nttdata.dio.orderservice.dto.OrderItemRequest;
import com.nttdata.dio.orderservice.enuns.OrderStatus;
import com.nttdata.dio.orderservice.model.Order;
import com.nttdata.dio.orderservice.model.OrderItem;
import com.nttdata.dio.orderservice.service.OrderBusinessService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ExpressOrderFactory implements OrderFactory {

    private final OrderBusinessService businessService;
    private static final BigDecimal EXPRESS_FEE = new BigDecimal("10.00");

    public ExpressOrderFactory(OrderBusinessService businessService) {
        this.businessService = businessService;
    }

    @Override
    public Order createOrder(CreateOrderRequest request) {
        businessService.validateOrderCreation(request);

        List<OrderItem> orderItems = request.items().stream()
                .map(this::createOrderItem)
                .toList();

        // Adiciona taxa express
        BigDecimal subtotal = businessService.calculateOrderTotal(orderItems);
        BigDecimal totalAmount = subtotal.add(EXPRESS_FEE);

        return Order.builder()
                .id("EXP-" + UUID.randomUUID().toString())
                .customerId(request.customerId())
                .items(orderItems)
                .totalAmount(totalAmount)
                .createdAt(OffsetDateTime.now())
                .status(OrderStatus.CONFIRMED) // Pedidos expressos já são confirmados
                .build();
    }

    private OrderItem createOrderItem(OrderItemRequest request) {
        return OrderItem.builder()
                .id("EXP-ITM-" + UUID.randomUUID().toString())
                .productId(request.productId())
                .quantity(request.quantity())
                .unitPrice(request.unitPrice())
                .build();
    }
}