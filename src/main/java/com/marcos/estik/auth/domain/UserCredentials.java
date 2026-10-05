package com.marcos.estik.auth.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter 
@RequiredArgsConstructor 
public class UserCredentials {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Boolean active;
}
