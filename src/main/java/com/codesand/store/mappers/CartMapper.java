package com.codesand.store.mappers;

import com.codesand.store.dtos.CartDto;
import com.codesand.store.dtos.CartItemDto;
import com.codesand.store.entities.Cart;
import com.codesand.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
