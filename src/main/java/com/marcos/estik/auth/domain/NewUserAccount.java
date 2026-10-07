package com.marcos.estik.auth.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class NewUserAccount {
    String username;
    String email;
    String password;
    UUID facility;
    
}