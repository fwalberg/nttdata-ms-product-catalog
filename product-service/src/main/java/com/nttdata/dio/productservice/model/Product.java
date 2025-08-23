package com.nttdata.dio.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public record Product(
      @Id @GeneratedValue
      Long id,
      String name,
      String description,
      Double price
) {
}
