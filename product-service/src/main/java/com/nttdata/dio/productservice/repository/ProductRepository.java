package com.nttdata.dio.productservice.repository;

import com.nttdata.dio.productservice.dto.ProductDTO;
import com.nttdata.dio.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<ProductDTO> findByNameIgnoreCase(String name);
    List<ProductDTO> findByNameContainingIgnoreCase(String namePart);
}
