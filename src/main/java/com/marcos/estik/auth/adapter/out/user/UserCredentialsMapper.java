package com.marcos.estik.auth.adapter.out.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.auth.domain.UserAuth;
import com.marcos.estik.user.application.view.UserView;

@Mapper(componentModel = "spring")
public interface UserCredentialsMapper {
    
    @Mapping(target = ".", source = "user")
    UserAuth toCredentials(UserView user);
    
}
