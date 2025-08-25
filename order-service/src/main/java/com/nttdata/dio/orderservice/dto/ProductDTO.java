package com.nttdata.dio.orderservice.dto;

public record ProductDTO(
        Long id,
        String name,
        String description,
        Double price
) {
}
