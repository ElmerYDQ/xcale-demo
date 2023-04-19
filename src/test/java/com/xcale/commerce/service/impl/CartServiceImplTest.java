package com.xcale.commerce.service.impl;

import com.xcale.commerce.mapper.CartMapper;
import com.xcale.commerce.mapper.ProductMapper;
import com.xcale.commerce.model.dto.CartDto;
import com.xcale.commerce.model.dto.ProductDto;
import com.xcale.commerce.model.entity.CartEntity;
import com.xcale.commerce.repository.CartRepository;
import com.xcale.commerce.util.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
  @Mock private CartRepository cartRepository;
  @Mock private CartMapper cartMapper;
  @Spy private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

  @InjectMocks private CartServiceImpl cartService;

  @Test
  void create() {
    CartDto cartDto = FileUtils.loadObject("data/model/dto/cartDto.json", CartDto.class);
    CartDto cartDto1 = FileUtils.loadObject("data/model/dto/cartDto-id.json", CartDto.class);
    CartEntity cartEntity =
        FileUtils.loadObject("data/model/entity/cartEntity.json", CartEntity.class);
    CartEntity cartEntity1 =
        FileUtils.loadObject("data/model/entity/cartEntity-id.json", CartEntity.class);
    cartEntity.setCreated(LocalDateTime.now());

    when(cartMapper.toEntity(any(CartDto.class))).thenReturn(cartEntity);
    when(cartRepository.save(any(CartEntity.class))).thenReturn(cartEntity1);
    when(cartMapper.toDto(any(CartEntity.class))).thenReturn(cartDto1);

    CartDto result = cartService.create(cartDto);

    assertNotNull(result);
    assertNotNull(result.getProducts());

    verify(cartRepository).save(any(CartEntity.class));
    verify(cartMapper).toDto(any(CartEntity.class));
  }

  @Test
  void addProducts() throws IOException {
    CartDto cartDto = FileUtils.loadObject("data/model/dto/cartDto-id.json", CartDto.class);
    List<ProductDto> productDtoList =
        FileUtils.loadObjectList("data/model/dto/productDto-list.json", ProductDto.class);
    CartEntity cartEntity =
        FileUtils.loadObject("data/model/entity/cartEntity-id.json", CartEntity.class);

    when(cartRepository.update(anyInt(), any())).thenReturn(cartEntity);
    when(cartMapper.toDto(any(CartEntity.class))).thenReturn(cartDto);

    CartDto result = cartService.addProducts(1, productDtoList);

    assertNotNull(result);
    assertNotNull(result.getProducts());

    verify(cartRepository).update(anyInt(), any());
  }

  @Test
  void getById() {
    CartDto cartDto = FileUtils.loadObject("data/model/dto/cartDto-id.json", CartDto.class);
    CartEntity cartEntity =
        FileUtils.loadObject("data/model/entity/cartEntity-id.json", CartEntity.class);

    when(cartRepository.findById(anyInt())).thenReturn(cartEntity);
    when(cartMapper.toDto(any(CartEntity.class))).thenReturn(cartDto);

    CartDto result = cartService.getById(1);

    assertNotNull(result);
    assertNotNull(result.getProducts());

    verify(cartRepository).findById(anyInt());
  }

  @Test
  void deleteById() {
    doNothing().when(cartRepository).delete(1);

    cartService.deleteById(1);

    verify(cartRepository).delete(anyInt());
  }
}
