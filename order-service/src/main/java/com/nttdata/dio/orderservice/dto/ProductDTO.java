package com.nttdata.dio.orderservice.dto;

public record ProductDTO(
        Long productId,
        String name,
        String description,
        Double price
) {
}
