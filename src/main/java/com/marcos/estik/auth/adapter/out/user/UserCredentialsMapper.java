package com.marcos.estik.auth.adapter.out.user;

import org.mapstruct.Mapper;

import com.marcos.estik.auth.domain.UserAuth;
import com.marcos.estik.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserCredentialsMapper {
    UserAuth toCredentials(User user);
}
