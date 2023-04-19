package com.xcale.commerce.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductEntity {
  private int id;
  private String description;
  private BigDecimal amount;
}
