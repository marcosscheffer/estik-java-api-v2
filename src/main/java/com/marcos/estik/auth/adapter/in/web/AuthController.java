package com.marcos.estik.auth.adapter.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.auth.adapter.in.web.mapper.AuthWebMapper;
import com.marcos.estik.auth.adapter.in.web.request.LoginRequest;
import com.marcos.estik.auth.adapter.in.web.request.RegisterRequest;
import com.marcos.estik.auth.adapter.in.web.response.TokenResponse;
import com.marcos.estik.auth.adapter.in.web.response.UserAuthResponse;
import com.marcos.estik.auth.application.port.in.LoginPort;
import com.marcos.estik.auth.application.port.in.RegisterPort;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/auth")
@RequiredArgsConstructor 
public class AuthController {

    private final LoginPort loginPort;
    private final RegisterPort registerPort;
    private final AuthWebMapper authWebMapper;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
        @RequestBody @Valid LoginRequest request
    ) {
        TokenResponse tokenResponse = authWebMapper.toResponseToken(
            loginPort.execute(
                authWebMapper.toCommandLogin(request)
            )
        );
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserAuthResponse> register(
        @RequestBody @Valid RegisterRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        UserAuthResponse userResponse = authWebMapper.toResponseUser(
            registerPort.execute(
                authWebMapper.toCommandRegister(request)
            )
        );

        URI uri = uriBuilder
            .path("/users/{id}")
            .buildAndExpand(userResponse.id())
            .toUri();
        
        return ResponseEntity.created(uri).body(userResponse);
    }
}
