package com.marcos.estik.user.application.command;

import java.util.UUID;

import com.marcos.estik.shared.domain.user.RoleEnum;

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
    UUID facilityId;
    Boolean active;
    RoleEnum role;
    RoleEnum actorRole;
}
