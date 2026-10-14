package com.marcos.estik.auth.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.shared.domain.user.RoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class UserAuth {
    UUID id;
    String username;
    String email;
    String password;
    UUID facilityId;
    RoleEnum role;
    Instant createdAt;
    Instant updatedAt;
    Boolean active;
}
