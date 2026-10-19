package com.marcos.estik.departament.application.command;

import java.util.UUID;

public record UpdateDepartamentCommand(
    String name,
    String code
) {
    
}
