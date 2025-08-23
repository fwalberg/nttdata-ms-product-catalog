package com.nttdata.dio.productservice.service;

import com.nttdata.dio.productservice.dto.ProductDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO save(ProductDTO productDTO);
    void deleteById(Long id);

    ProductDTO findByName(String name);
    List<ProductDTO> findByNameContaining(String namePart);
}
