package com.marcos.estik.user.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class User {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private RoleEnum role;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean active;
}
