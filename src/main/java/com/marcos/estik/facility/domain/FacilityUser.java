package com.marcos.estik.facility.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class FacilityUser {
    private UUID userId;
    private String username;
    private String email;
    private Boolean active;
}
