package com.nttdata.dio.orderservice.mapper;

import com.nttdata.dio.orderservice.dto.ProductDTO;
import com.nttdata.dio.orderservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
    List<ProductDTO> toProductDTOList(List<Product> productList);
    List<Product> toProductList(List<ProductDTO> productDTOList);
}
