package com.marcos.estik.user.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.user.adapter.in.web.response.UserResponse;
import com.marcos.estik.user.application.result.UserData;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    UserResponse toResponse(UserData userData);
}
