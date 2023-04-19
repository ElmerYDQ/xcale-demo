package com.xcale.commerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDto {
  private int id;
  private List<ProductDto> products;
}
