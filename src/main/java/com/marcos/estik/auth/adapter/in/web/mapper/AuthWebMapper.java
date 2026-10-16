package com.marcos.estik.auth.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.auth.adapter.in.web.request.LoginRequest;
import com.marcos.estik.auth.adapter.in.web.request.RegisterRequest;
import com.marcos.estik.auth.adapter.in.web.response.TokenResponse;
import com.marcos.estik.auth.adapter.in.web.response.UserAuthResponse;
import com.marcos.estik.auth.application.command.LoginCommand;
import com.marcos.estik.auth.application.command.RegisterCommand;
import com.marcos.estik.auth.application.result.AuthTokens;
import com.marcos.estik.auth.domain.UserAuth;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {
    LoginCommand toCommandLogin(LoginRequest request);

    TokenResponse toResponseToken(AuthTokens authTokens);

    RegisterCommand toCommandRegister(RegisterRequest request);

    UserAuthResponse toResponseUser(UserAuth user);

}
