package com.marcos.estik.user.adapter.in.web.request;

import java.util.UUID;

import com.marcos.estik.user.domain.RoleEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
    @NotNull 
    UUID id,
    @Email 
    String email,
    @NotBlank 
    String username,
    @NotNull
    UUID facilityId,
    @NotNull 
    Boolean active,
    @NotNull 
    RoleEnum role
) {
    
}
