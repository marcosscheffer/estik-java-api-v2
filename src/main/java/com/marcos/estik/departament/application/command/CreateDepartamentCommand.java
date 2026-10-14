package com.marcos.estik.departament.application.command;

import java.util.UUID;

public record CreateDepartamentCommand(
    String name,
    String code,
    UUID facilityId
) {
    
}
