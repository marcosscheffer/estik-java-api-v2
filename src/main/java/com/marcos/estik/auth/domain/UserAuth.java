package com.marcos.estik.auth.domain;

import java.time.Instant;
import java.util.UUID;


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
    UUID roleId;
    Instant createdAt;
    Instant updatedAt;
    Boolean active;
}
