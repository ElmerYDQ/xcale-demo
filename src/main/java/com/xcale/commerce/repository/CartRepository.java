package com.xcale.commerce.repository;

import com.xcale.commerce.model.entity.CartEntity;
import com.xcale.commerce.model.entity.ProductEntity;

import java.util.List;

public interface CartRepository {
  CartEntity save(CartEntity cartEntity);

  CartEntity update(int cartId, List<ProductEntity> products);

  CartEntity findById(int id);

  void delete(int id);
}
