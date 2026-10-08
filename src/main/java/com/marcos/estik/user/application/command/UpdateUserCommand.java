package com.marcos.estik.user.application.command;

import java.util.UUID;

import com.marcos.estik.user.domain.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor 
public class UpdateUserCommand {
    UUID id;
    String email;
    String username;
    UUID facility;
    Boolean active;
    RoleEnum role;
    RoleEnum actorRole;
}
