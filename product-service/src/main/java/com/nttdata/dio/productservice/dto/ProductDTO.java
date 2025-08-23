package com.nttdata.dio.productservice.dto;

public record ProductDTO(
        Long id,
        String name,
        String description,
        Double price
) {
}
