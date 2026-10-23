package com.marcos.estik.departament.adapter.in.web.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDepartamentRequest(
    @NotNull 
    UUID facilityId,
    @NotBlank 
    String name,
    @NotBlank 
    String code
) {
}
