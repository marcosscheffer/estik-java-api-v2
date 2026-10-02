package com.marcos.estik.auth.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.auth.adapter.in.web.request.LoginRequest;
import com.marcos.estik.auth.adapter.in.web.request.RegisterRequest;
import com.marcos.estik.auth.adapter.in.web.response.TokenResponse;
import com.marcos.estik.auth.application.command.LoginCommand;
import com.marcos.estik.auth.application.command.RegisterCommand;
import com.marcos.estik.auth.application.result.AuthTokens;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {
    LoginCommand toCommandLogin(LoginRequest request);

    TokenResponse toResponse(AuthTokens authTokens);

    RegisterCommand toCommandRegister(RegisterRequest request);

}
