package com.marcos.estik.auth.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class UserAuth {
    private UUID id;
    private String username;
    private String email;
    private String createdAt;
    private String updatedAt;
    private String active;
}
