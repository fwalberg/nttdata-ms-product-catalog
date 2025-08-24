package com.nttdata.dio.productservice.service;

import com.nttdata.dio.productservice.dto.ProductDTO;
import com.nttdata.dio.productservice.exception.ProductInvalidArgumentException;
import com.nttdata.dio.productservice.exception.ProductNotFoundException;
import com.nttdata.dio.productservice.mapper.ProductMapper;
import com.nttdata.dio.productservice.model.Product;
import com.nttdata.dio.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
        this.mapper = ProductMapper.INSTANCE;
    }

    @Override
    public List<ProductDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toProductDTO)
                .toList();
    }

    @Override
    public ProductDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toProductDTO)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        if (productDTO.price() <= 0) {
            throw new ProductInvalidArgumentException("Price: " + productDTO.price());
        }
        Product product = repository.save(mapper.toProduct(productDTO));
        return mapper.toProductDTO(product);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
       if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        if (productDTO.price() <= 0) {
            throw new ProductInvalidArgumentException("Price: " + productDTO.price());
        }
        Product product = repository.save(mapper.toProduct(productDTO));
        return mapper.toProductDTO(product);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Override
    public ProductDTO findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ProductInvalidArgumentException("Name: " + name);
        }
        Product product = mapper.toProduct(repository.findByNameIgnoreCase(name).orElseThrow(
                () -> new ProductNotFoundException("Product with name " + name + " not found")));
        return mapper.toProductDTO(product);
    }

    @Override
    public List<ProductDTO> findByNameContaining(String namePart) {
        if (namePart == null || namePart.trim().isEmpty()) {
            throw new ProductInvalidArgumentException("Name: " + namePart);
        }
        return repository.findByNameContainingIgnoreCase(namePart);
    }
}