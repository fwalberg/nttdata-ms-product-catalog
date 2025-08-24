package com.nttdata.dio.orderservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Product {
      Long id;
      String name;
      String description;
      Double price;
}
