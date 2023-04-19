package com.xcale.commerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
  private int id;
  private String description;
  private BigDecimal amount;
}
