package com.nttdata.dio.productservice.repository;

import com.nttdata.dio.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
