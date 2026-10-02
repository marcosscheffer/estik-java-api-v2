package com.marcos.estik.auth.adapter.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.auth.adapter.in.web.mapper.AuthWebMapper;
import com.marcos.estik.auth.adapter.in.web.request.LoginRequest;
import com.marcos.estik.auth.adapter.in.web.response.TokenResponse;
import com.marcos.estik.auth.application.port.in.LoginPort;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/auth")
@RequiredArgsConstructor 
public class AuthController {

    private final LoginPort loginPort;
    private final AuthWebMapper authWebMapper;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        TokenResponse tokenResponse = authWebMapper.toResponse(loginPort.execute(authWebMapper.toCommand(request)));
        return ResponseEntity.ok(tokenResponse);
    }
}
