package com.nttdata.dio.orderservice.repository;

import com.nttdata.dio.orderservice.enuns.OrderStatus;
import com.nttdata.dio.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface Productclient extends JpaRepository<Order, String> {
    List<Order> findByCustomerId(String customerId);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCreatedAtBetween(OffsetDateTime start, OffsetDateTime end);
}
