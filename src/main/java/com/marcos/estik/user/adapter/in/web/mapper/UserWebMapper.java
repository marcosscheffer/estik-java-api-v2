package com.marcos.estik.user.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.user.adapter.in.web.response.UserResponseSummary;
import com.marcos.estik.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    UserResponseSummary toResponseSummary(User user);

}
