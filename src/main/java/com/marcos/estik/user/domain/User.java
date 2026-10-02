package com.marcos.estik.user.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public static User create(String username, String email, String password) {
        return new User(
            UUID.randomUUID(),
            username,
            email,
            password,
            RoleEnum.USER,
            Instant.now(),
            Instant.now(),
            Boolean.FALSE
        );
    }
}
