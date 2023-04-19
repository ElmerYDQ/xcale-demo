package com.xcale.commerce.service.impl;

import com.xcale.commerce.mapper.CartMapper;
import com.xcale.commerce.mapper.ProductMapper;
import com.xcale.commerce.model.dto.CartDto;
import com.xcale.commerce.model.dto.ProductDto;
import com.xcale.commerce.model.entity.CartEntity;
import com.xcale.commerce.repository.CartRepository;
import com.xcale.commerce.service.CartService;
import com.xcale.commerce.util.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CartServiceImplTest {
  @Autowired private CartRepository cartRepository;
  @Autowired private CartMapper cartMapper;
  @Autowired private ProductMapper productMapper;

  @Autowired private CartService cartService;

  @Test
  void create() {
    CartDto cartDto = FileUtils.loadObject("data/model/dto/cartDto.json", CartDto.class);
    CartEntity cartEntity =
            FileUtils.loadObject("data/model/entity/cartEntity-id.json", CartEntity.class);
    cartEntity.setCreated(LocalDateTime.now());

    when(cartRepository.save(any(CartEntity.class))).thenReturn(cartEntity);

    CartDto result = cartService.create(cartDto);

    assertNotNull(result);
    assertNotNull(result.getProducts());
    assertEquals(result.getProducts(), cartDto.getProducts());

    verify(cartRepository).save(any(CartEntity.class));
    verify(cartMapper).toEntity(any(CartDto.class));
    verify(cartMapper).toDto(any(CartEntity.class));
  }

  @Test
  void addProducts() {}

  @Test
  void getById() {}

  @Test
  void deleteById() {}
}
