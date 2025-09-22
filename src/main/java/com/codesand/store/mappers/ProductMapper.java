package com.codesand.store.mappers;

import com.codesand.store.dtos.ProductDto;
import com.codesand.store.dtos.UpdateUserRequest;
import com.codesand.store.entities.Product;

import com.codesand.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id" ,target = "categoryId")
    ProductDto toDto (Product product);
    Product toEntity (ProductDto product);
    @Mapping(target = "id", ignore = true)
    void update(ProductDto request,@MappingTarget Product product);

}
