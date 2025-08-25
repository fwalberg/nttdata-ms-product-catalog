package com.nttdata.dio.orderservice.model;

import lombok.Data;

@Data
public class Product {
      Long id;
      String name;
      String description;
      Double price;
}
