package com.nttdata.dio.orderservice.service;

import com.nttdata.dio.orderservice.dto.ProductDTO;
import com.nttdata.dio.orderservice.feign.ProductFeignClient;
import com.nttdata.dio.productservice.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductFeignClient productFeignClient;

    public ProductService(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    public ProductDTO findById(Long productId) {
        try {
            ProductDTO product = productFeignClient.findById(productId).getBody();
            if (product == null) {
                throw new ProductNotFoundException("Product not found or inactive: " + productId);
            }
            return product;
        } catch (Exception e) {
            throw new ProductNotFoundException("Product not found: " + productId);
        }
    }

    public List<ProductDTO> findByIds(List<Long> productIds) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        try {
            for (Long id : productIds) {
                ProductDTO product = productFeignClient.findById(id).getBody();
                productDTOList.add(product);
            }
            return productDTOList;
        } catch (Exception e) {
            throw new ProductNotFoundException("Error fetching products");
        }
    }

}
