package com.xcale.commerce.service;

import com.xcale.commerce.model.dto.CartDto;
import com.xcale.commerce.model.dto.ProductDto;

import java.util.List;

public interface CartService {
  CartDto create(CartDto cartDto);

  CartDto addProducts(int cartId, List<ProductDto> products);

  CartDto getById(int id);

  void deleteById(int id);
}
