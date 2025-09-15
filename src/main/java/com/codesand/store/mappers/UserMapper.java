package com.codesand.store.mappers;

import com.codesand.store.dtos.RegisterUserRequest;
import com.codesand.store.dtos.UserDto;
import com.codesand.store.entities.User;
import org.mapstruct.Mapper;


@Mapper(componentModel= "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(RegisterUserRequest request);
}
