package com.xcale.commerce.service.impl;

import com.xcale.commerce.mapper.CartMapper;
import com.xcale.commerce.mapper.ProductMapper;
import com.xcale.commerce.model.dto.CartDto;
import com.xcale.commerce.model.dto.ProductDto;
import com.xcale.commerce.repository.CartRepository;
import com.xcale.commerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
  private final CartRepository cartRepository;
  private final CartMapper cartMapper;
  private final ProductMapper productMapper;

  @Override
  public CartDto create(CartDto cartDto) {
    return cartMapper.toDto(cartRepository.save(cartMapper.toEntity(cartDto)));
  }

  @Override
  public CartDto addProducts(int cartId, List<ProductDto> products) {
    return cartMapper.toDto(
        cartRepository.update(
            cartId, products.stream().map(productMapper::toEntity).collect(Collectors.toList())));
  }

  @Override
  public CartDto getById(int id) {
    return cartMapper.toDto(cartRepository.findById(id));
  }

  @Override
  public void deleteById(int id) {
    cartRepository.delete(id);
  }
}
