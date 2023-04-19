package com.xcale.commerce.mapper;

import com.xcale.commerce.model.dto.ProductDto;
import com.xcale.commerce.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
  ProductDto toDto(ProductEntity productEntity);

  ProductEntity toEntity(ProductDto productDto);
}
