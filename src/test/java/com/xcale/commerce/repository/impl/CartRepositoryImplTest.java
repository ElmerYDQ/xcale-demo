package com.xcale.commerce.repository.impl;

import com.xcale.commerce.model.entity.CartEntity;
import com.xcale.commerce.model.entity.ProductEntity;
import com.xcale.commerce.util.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CartRepositoryImplTest {
  @InjectMocks private CartRepositoryImpl cartRepository;

  @BeforeEach
  void setup() {
    ReflectionTestUtils.setField(cartRepository, "minutes", 10);
    CartEntity cartEntity =
        FileUtils.loadObject("data/model/entity/cartEntity.json", CartEntity.class);
    cartEntity.setCreated(LocalDateTime.now());
    cartRepository.save(cartEntity);
  }

  @Test
  void Given_CartAndProducts_When_Create_Then_Ok() {
    CartEntity cartEntity =
        FileUtils.loadObject("data/model/entity/cartEntity.json", CartEntity.class);
    cartEntity.setCreated(LocalDateTime.now());

    CartEntity result = cartRepository.save(cartEntity);

    assertNotNull(result);
    assertEquals(result.getProducts(), cartEntity.getProducts());
  }

  @Test
  void update() throws IOException {
    List<ProductEntity> productEntityList =
        FileUtils.loadObjectList("data/model/entity/productEntity-list.json", ProductEntity.class);

    CartEntity cartEntity = cartRepository.update(1, productEntityList);

    assertNotNull(cartEntity);
  }
}
