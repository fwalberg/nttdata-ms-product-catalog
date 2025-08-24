package com.nttdata.dio.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
public class Product {
      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
      Long id;
      String name;
      String description;
      Double price;
}
