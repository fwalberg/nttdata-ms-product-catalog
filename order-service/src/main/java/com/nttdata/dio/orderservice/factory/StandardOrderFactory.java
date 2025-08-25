package com.nttdata.dio.orderservice.factory;

import com.nttdata.dio.orderservice.dto.CreateOrderRequest;
import com.nttdata.dio.orderservice.dto.OrderItemRequest;
import com.nttdata.dio.orderservice.dto.ProductDTO;
import com.nttdata.dio.orderservice.enuns.OrderStatus;
import com.nttdata.dio.orderservice.model.Order;
import com.nttdata.dio.orderservice.model.OrderItem;
import com.nttdata.dio.orderservice.service.OrderBusinessService;
import com.nttdata.dio.orderservice.service.ProductService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StandardOrderFactory implements OrderFactory {
    private final OrderBusinessService businessService;
    private final ProductService productService;

    public StandardOrderFactory(OrderBusinessService businessService, ProductService productService) {
        this.businessService = businessService;
        this.productService = productService;
    }

    @Override
    public Order createOrder(CreateOrderRequest request) {

        businessService.validateOrderCreation(request);

        Map<Long, ProductDTO> productMap = getProductMap(request);

        List<OrderItem> orderItems = request.items().stream()
                .map(itemRequest -> createOrderItem(itemRequest, productMap))
                .toList();

        BigDecimal totalAmount = businessService.calculateOrderTotal(orderItems);

        return Order.builder()
                .id(generateOrderId())
                .customerId(request.customerId())
                .items(orderItems)
                .totalAmount(totalAmount)
                .createdAt(OffsetDateTime.now())
                .status(OrderStatus.PENDING)
                .build();
    }

    private Map<Long, ProductDTO> getProductMap(CreateOrderRequest request) {
        List<Long> productIds = request.items().stream()
                .map(OrderItemRequest::productId)
                .distinct()
                .toList();

        return productService.findByIds(productIds)
                .stream()
                .collect(Collectors.toMap(ProductDTO::id, Function.identity()));
    }

    private OrderItem createOrderItem(OrderItemRequest request, Map<Long, ProductDTO> productMap) {
        ProductDTO product = productMap.get(request.productId());

        String productName = request.productName() != null ?
                request.productName() : product.name();
        BigDecimal unitPrice = request.unitPrice() != null ?
                request.unitPrice() : BigDecimal.valueOf(product.price());

        return OrderItem.builder()
                .id(generateItemId())
                .productId(request.productId())
                .productName(productName)
                .quantity(request.quantity())
                .unitPrice(unitPrice)
                .build();
    }

    private String generateOrderId() {
        return "ORD-" + UUID.randomUUID().toString();
    }

    private String generateItemId() {
        return "ITM-" + UUID.randomUUID().toString();
    }
}

