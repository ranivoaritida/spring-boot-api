package com.codesand.store.mappers;

import com.codesand.store.dtos.RegisterUserRequest;
import com.codesand.store.dtos.UpdateUserRequest;
import com.codesand.store.dtos.UserDto;
import com.codesand.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel= "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request,@MappingTarget User user);
}
