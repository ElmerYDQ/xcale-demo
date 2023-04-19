package com.xcale.commerce.mapper;

import com.xcale.commerce.model.dto.CartDto;
import com.xcale.commerce.model.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {ProductMapper.class},
    imports = {LocalDateTime.class})
public interface CartMapper {
  CartDto toDto(CartEntity cartEntity);

  @Mapping(target = "created", expression = "java(LocalDateTime.now())")
  CartEntity toEntity(CartDto cartDto);
}
