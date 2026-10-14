package com.marcos.estik.departament.application.command;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;

public record SearchDepartamentCommand(
    String name,
    String code,
    UUID facilityId,
    PageQuery page
) {
    
}
