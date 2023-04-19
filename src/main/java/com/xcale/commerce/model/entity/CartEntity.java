package com.xcale.commerce.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CartEntity {
  private int id;
  private List<ProductEntity> products;
  private LocalDateTime created;
}
