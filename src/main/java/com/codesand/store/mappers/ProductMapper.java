package com.codesand.store.mappers;

import com.codesand.store.dtos.ProductDto;
import com.codesand.store.entities.Product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id" ,target = "categoryId")
    ProductDto toDto (Product product);
}
