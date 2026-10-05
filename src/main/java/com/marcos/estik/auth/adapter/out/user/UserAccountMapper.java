package com.marcos.estik.auth.adapter.out.user;

import org.mapstruct.Mapper;

import com.marcos.estik.auth.domain.NewUserAccount;
import com.marcos.estik.user.application.command.CreateUserCommand;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {
    CreateUserCommand toCommand(NewUserAccount user);
}
