package com.marcos.estik.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class NewUserAccount {
    String username;
    String email;
    String password;
    
}