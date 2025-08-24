package com.nttdata.dio.orderservice.feign;

import com.nttdata.dio.orderservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8100")
public interface ProductFeignClient {
    
    @GetMapping("/products")
    ResponseEntity<List<ProductDTO>> findAll();

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id);
}
