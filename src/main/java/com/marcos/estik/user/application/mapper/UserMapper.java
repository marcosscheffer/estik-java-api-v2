package com.marcos.estik.user.application.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.user.application.result.UserData;
import com.marcos.estik.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserData toResult(User user);
}
