package com.marcos.estik.auth.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class FacilityAuth {
    UUID id;
    String name;
    String code;
}
